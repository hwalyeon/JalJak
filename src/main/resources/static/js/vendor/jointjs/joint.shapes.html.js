(function(joint, util, V) {

    var Element = joint.dia.Element;
    var ElementView = joint.dia.ElementView;

    Element.define('html.Element', {
        size: { width: 250, height: 228 },
        fields: {
            header: 'Task',
            name: '',
            resource: '',
            state: ''
        },
        attrs: {
            placeholder: {
                refWidth: '100%',
                refHeight: '100%',
                fill: 'transparent',
                stroke: '#D4D4D4'
            }
        },
        inPorts: [],
        outPorts: [],
        ports: {
            groups: {
                'in': {
                    position: {
                        name: 'left'
                    },
                    attrs: {
                        '.port-label': {
                            fill: '#000'
                        },
                        '.port-body': {
                            fill: '#fff',
                            stroke: '#000',
                            r: 10,
                            magnet: true
                        }
                    },
                    label: {
                        position: {
                            name: 'left',
                            args: {
                                y: 10
                            }
                        }
                    }
                },
                'out': {
                    position: {
                        name: 'right'
                    },
                    attrs: {
                        '.port-label': {
                            fill: '#000'
                        },
                        '.port-body': {
                            fill: '#fff',
                            stroke: '#000',
                            r: 10,
                            magnet: true
                        }
                    },
                    label: {
                        position: {
                            name: 'right',
                            args: {
                                y: 10
                            }
                        }
                    }
                }
            }
        }
    }, {
        markup: [{
            tagName: 'rect',
            selector: 'placeholder'
        }],
        htmlMarkup: [{
            tagName: 'div',
            className: 'html-element',
            selector: 'htmlRoot',
            groupSelector: 'field',
            style: {
                'position': 'absolute',
                'pointer-events': 'none',
                'user-select': 'none',
                'box-sizing': 'border-box'
            },
            attributes: {
                'data-attribute': 'state'
            },
            children: [{
                tagName: 'label',
                className: 'html-element-header',
                groupSelector: 'field',
                attributes: {
                    'data-attribute': 'header'
                }
            }, {
                tagName: 'label',
                className: 'html-element-label',
                textContent: 'Name',
                children: [{
                    tagName: 'input',
                    className: 'html-element-field',
                    groupSelector: 'field',
                    attributes: {
                        'data-attribute': 'name',
                        'placeholder': 'Name'
                    },
                    style: {
                        'pointer-events': 'auto'
                    }
                }]
            }, {
                tagName: 'label',
                className: 'html-element-label',
                textContent: 'Resource',
                children: [{
                    tagName: 'select',
                    className: 'html-element-field',
                    groupSelector: 'field',
                    attributes: {
                        'data-attribute': 'resource'
                    },
                    style: {
                        'pointer-events': 'auto'
                    },
                    children: [{
                        tagName: 'option',
                        textContent: 'Resource',
                        attributes: {
                            'value': '',
                            'disabled': 'true'
                        }
                    }, {
                        tagName: 'option',
                        textContent: 'John',
                        attributes: {
                            'value': 'john'
                        }
                    }, {
                        tagName: 'option',
                        textContent: 'Mary',
                        attributes: {
                            'value': 'mary'
                        }
                    }, {
                        tagName: 'option',
                        textContent: 'Bob',
                        attributes: {
                            'value': 'bob'
                        }
                    }]
                }]
            }]
        }],
        portMarkup: '<circle class="port-body"/>',
        portLabelMarkup: '<text class="port-label"/>',
        initialize: function() {
            this.on('change:inPorts change:outPorts', this.updatePortItems, this);
            this.updatePortItems();
        },

        updatePortItems: function(model, changed, opt) {

            // Make sure all ports are unique.
            var inPorts = util.uniq(this.get('inPorts'));
            var outPorts = util.difference(util.uniq(this.get('outPorts')), inPorts);

            var inPortItems = this.createPortItems('in', inPorts);
            var outPortItems = this.createPortItems('out', outPorts);

            this.prop('ports/items', inPortItems.concat(outPortItems), util.assign({ rewrite: true }, opt));
        },

        createPortItem: function(group, port) {

            return {
                id: port,
                group: group,
                attrs: {
                    '.port-label': {
                        text: port
                    }
                }
            };
        },

        createPortItems: function(group, ports) {

            return util.toArray(ports).map(this.createPortItem.bind(this, group));
        },

        _addGroupPort: function(port, group, opt) {

            var ports = util.get(group);
            return util.set(group, Array.isArray(ports) ? ports.concat(port) : [port], opt);
        },

        addOutPort: function(port, opt) {

            return util._addGroupPort(port, 'outPorts', opt);
        },

        addInPort: function(port, opt) {

            return util._addGroupPort(port, 'inPorts', opt);
        },

        _removeGroupPort: function(port, group, opt) {

            return util.set(group, without(this.get(group), port), opt);
        },

        removeOutPort: function(port, opt) {

            return util._removeGroupPort(port, 'outPorts', opt);
        },

        removeInPort: function(port, opt) {

            return util._removeGroupPort(port, 'inPorts', opt);
        },

        _changeGroup: function(group, properties, opt) {

            return this.prop('ports/groups/' + group, isObject(properties) ? properties : {}, opt);
        },

        changeInGroup: function(properties, opt) {

            return util._changeGroup('in', properties, opt);
        },

        changeOutGroup: function(properties, opt) {

            return util._changeGroup('out', properties, opt);
        }
    });

    // Custom view for JointJS HTML element that displays an HTML <div></div> above the SVG Element.
    joint.shapes.html.ElementView = ElementView.extend({

        html: null,

        presentationAttributes: ElementView.addPresentationAttributes({
            position: ['HTML_UPDATE'],
            size: ['HTML_UPDATE'],
            fields: ['HTML_FIELD_UPDATE']
        }),

        // Run these upon first render
        initFlag: ElementView.prototype.initFlag.concat([
            'HTML_UPDATE',
            'HTML_FIELD_UPDATE'
        ]),

        confirmUpdate: function() {
            var flags = ElementView.prototype.confirmUpdate.apply(this, arguments);
            console.log(flags);
            if (util.hasFlag(flags, 'HTML_UPDATE')) {
                this.updateHTML();
                flags = util.removeFlag(flags, 'HTML_UPDATE');
            }
            if (util.hasFlag(flags, 'HTML_FIELD_UPDATE')) {
                this.updateFields();
                flags = util.removeFlag(flags, 'HTML_FIELD_UPDATE');
            }
            return flags;
        },

        onRender: function() {
            this.removeHTMLMarkup();
            this.renderHTMLMarkup();
            return this;
        },

        renderHTMLMarkup: function() {
            var doc = util.parseDOMJSON(this.model.htmlMarkup, V.namespace.xhtml);
            var html = doc.selectors.htmlRoot;
            var fields = doc.groupSelectors.field;
            // React on all box changes. e.g. input change
            html.addEventListener('change', this.onFieldChange.bind(this), false);
            this.paper.htmlContainer.appendChild(doc.fragment);
            this.html = html;
            this.fields = fields;
        },

        removeHTMLMarkup: function() {
            var html = this.html;
            if (!html) return;
            this.paper.htmlContainer.removeChild(html);
            this.html = null;
            this.fields = null;
        },

        updateHTML: function() {
            var bbox = this.model.getBBox();
            var html = this.html;
            html.style.width = bbox.width + 'px';
            html.style.height = bbox.height + 'px';
            html.style.left = bbox.x + 'px';
            html.style.top = bbox.y + 'px';
        },

        onFieldChange: function(evt) {
            var input = evt.target;
            var attribute = input.dataset.attribute;
            if (attribute) {
                this.model.prop(['fields', attribute], input.value);
            }
        },

        updateFields: function() {
            this.fields.forEach(function(field) {
                var attribute = field.dataset.attribute;
                var value = this.model.prop(['fields', attribute]);
                switch (field.tagName.toUpperCase()) {
                    case 'LABEL':
                        field.textContent = value;
                        break;
                    case 'INPUT':
                    case 'SELECT':
                        field.value = value;
                        if (value) {
                            field.classList.remove('field-empty');
                        } else {
                            field.classList.add('field-empty');
                        }
                        break;
                    case 'DIV':
                        field.dataset[attribute] = value;
                        break;
                }
            }.bind(this));
        },

        onRemove: function() {
            this.removeHTMLMarkup();
        }
    });

})(joint, joint.util, V);
