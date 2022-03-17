/**
 * File Name   : input.number.component.js
 * Description : input 숫자 컴포넌트
 * Author      : boneis
 * Date        : 2018.12.03
 * Note        : IE에서 onchange 되지않아 onblur 사용
 */

Vue.component("input-number", {
    template: '<input type="text" ref="number"'
              + '    v-bind:value="formatValue"'
              + '    v-bind:maxlength="maxLength"'
              + '    v-on:input="onInput($event.target.value)"'
              + '    v-on:blur="onChange($event.target.value)" />',
    props: {
        value: {
            type: [String, Number],
            required: false,
            default: 0
        },
        min: {
            type: Number,
            required: false,
            default: 0
        },
        max: {
            type: Number,
            required: true
        },
        precision: {
            type: Number,
            required: false,
            default: 0
        },
        masked: {
            type: Boolean,
            required: false,
            default: true
        },
        blank: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    data: function() {
        return {
            originValue: 0,
            formatValue: "",
            cfmInputValue: "",
            maxLength: 0
        };
    },
    watch: {
        value: {
            immediate: true,
            handler: function(newVal, oldVal) {
                if (this.originValue !== newVal) {
                    // 결과값
                    var inputNumber = this.getInputNumber(newVal);
                    var result = this.getResultNumber(inputNumber);

                    // 이벤트 트리거
                    this.originValue = result;
                    this.$emit("input", result);
                }

                // 포맷값
                if (typeof oldVal === "undefined") {
                    this.formatValue = this.formater(newVal, "change");
                } else {
                    this.formatValue = this.formater(this.cfmInputValue, "input");
                }

                // console.log("watch", newVal, oldVal, this.originValue, this.cfmInputValue);
            }
        }
    },
    methods: {
        // input 이벤트
        onInput: function(val) {
            // 입력 숫자
            var inputNumber = this.getInputNumber(val);
            // console.log("onInput", val, inputNumber);

            // 커서 위치
            var cursorPosition = this.getCursorPosition(val);

            // input value 설정
            var inputValue = this.formater(inputNumber, "input");
            this.$refs.number.value = inputValue;

            // 커서 위치 설정
            cursorPosition = inputValue.length - cursorPosition;
            this.$refs.number.setSelectionRange(cursorPosition, cursorPosition);

            // 결과값
            var result = this.getResultNumber(inputNumber);

            // 이벤트 트리거
            if (this.value !== result) {
                this.originValue = result;
                this.$emit("input", result);
            }
        },

        // chage 이벤트
        onChange: function(val) {
            // 기호
            var symbol = this.getSymbol(val);

            // 기호 제거
            val = val.replace("-", "");

            // 입력 숫자
            var inputNumber = symbol + this.removeFrontZero(this.getInputNumber(val));

            // input value 설정
            this.$refs.number.value = this.formater(inputNumber, "change");

            // 결과값
            var result = this.getResultNumber(inputNumber);

            // 이벤트 트리거
            if (this.value !== result) {
                this.originValue = result;
                this.$emit("input", result);
            }

            // blur 이벤트
            this.$emit("blur");
        },

        // 포맷터
        formater: function(num, mode) {
            // Float
            if (this.precision > 0) {
                // 최대값 체크
                if (parseFloat(num) > this.max) {
                    num = this.max;
                }

                // 최소값 체크
                if (parseFloat(num) < this.min) {
                    num = this.min;
                }

                // 실수 구하기
                var float = this.getFloat(num, mode);

                // 2자리일경우 앞에 0 제거
                var integer = float.integer;

                if (integer.length == 2 && integer.indexOf("0") == 0) {
                    integer = this.removeFrontZero(integer);
                }

                // 결과값
                var result = integer + float.point + float.decimal;

                // 마스크 처리
                if (this.masked === true) {
                    result = this.addThousandComma(integer) + float.point + float.decimal;
                }

                return result;

            // Integer
            } else {
                if (this.isNull(num)) {
                    if (this.blank === true) {
                        return "";
                    } else {
                        return "0";
                    }
                }

                // 최대값 체크
                if (parseInt(num, 10) > this.max) {
                    num = this.max;
                }

                // 최소값 체크
                if (parseInt(num, 10) < this.min) {
                    num = this.min;
                }

                // 2자리일경우 앞에 0 제거
                num = this.toString(num);

                if (num.length == 2 && num.indexOf("0") == 0) {
                    num = this.removeFrontZero(num);
                }

                // 결과값
                var result = num;

                // 마스크 처리
                if (this.masked === true) {
                    result = this.addThousandComma(num);
                }

                return result;
            }
        },

        // 문자열 정수 구하기
        getStringInteger: function(val) {
            return this.toString(val).replace(/[^0-9]+/g, "");
        },

        // 문자열 실수 구하기
        getStringFloat: function(val) {
            return this.toString(val).replace(/[^0-9\.]+/g, "");
        },

        // 입력 숫자 구하기
        getInputNumber: function(val) {
            // 기호
            var symbol = this.getSymbol(val);

            // Float
            if (this.precision > 0) {
                // 소수점 개수
                var num = this.toString(val);
                var pointCnt = (num.match(/\./g) || []).length;

                // console.log("pointCnt", pointCnt, val, this.cfmInputValue);

                // 소수점 2개 체크
                if (pointCnt == 2 && !this.isNull(this.cfmInputValue)) {
                    num = this.cfmInputValue;
                } else {
                    this.cfmInputValue = symbol + this.getStringFloat(num);
                }

                return symbol + this.getStringFloat(num);

            // Integer
            } else {
                // 결과값
                var result = symbol + this.getStringInteger(val);

                // 확인 input값
                this.cfmInputValue = result;

                return result;
            }
        },

        // 숫자타입 결과 구하기
        getResultNumber: function(num) {
            if (this.isNull(num)) {
                if (this.blank === true) {
                    return null;
                } else {
                    return 0;
                }
            }

            // Float
            if (this.precision > 0) {
                // 결과값
                var result = parseFloat(num);

                // 최대값 체크
                if (result > this.max) {
                    result = this.max;
                }

                // 최소값 체크
                if (result < this.min) {
                    result = this.min;
                }

                return parseFloat(result);

            // Integer
            } else {
                // 결과값
                var result = parseInt(num, 10);

                // 최대값 체크
                if (result > this.max) {
                    result = this.max;
                }

                // 최소값 체크
                if (result < this.min) {
                    result = this.min;
                }

                return parseInt(result, 10);
            }
        },

        // 실수 구하기
        getFloat: function(val, mode) {
            var parts = this.toString(val).split(".");

            // 정수
            var integer = parts[0];

            if (this.isNull(integer)) {
                integer = "0";
            }

            // 소수
            var point = "";
            var decimal = "";

            if (parts.length > 1) {
                point = ".";
                decimal = parts[1];
            }

            if (mode == "change") {
                // 뒤에 0 채우기
                if (decimal.length < this.precision) {
                    decimal = this.fillBackZero(decimal, this.precision - decimal.length);
                }

                point = ".";
            }

            // 클경우 버림
            if (decimal.length > this.precision) {
                decimal = decimal.substr(0, this.precision);
            }

            return { integer: integer, point: point, decimal: decimal };
        },

        // 기호 구하기
        getSymbol: function(val) {
            var symbol = "";

            if (this.min < 0 && this.toString(val).indexOf("-") >= 0) {
                symbol = "-";
            }

            return symbol;
        },

        // null 체크
        isNull: function(str) {
            var type = typeof str;

            if (type === "undefined" || str == null) {
                return true;
            }

            if (type === "string" && str.trim() == "") {
                return true;
            }

            return false;
        },

        // not null 체크
        isNotNull: function(str) {
            return !this.isNull(str);
        },

        // 문자 변환
        toString: function(val) {
            var type = typeof val;

            if (type === "number") {
                return val.toString();
            } else if (type === "string") {
                return val;
            } else {
                return "0";
            }
        },

        // 앞에 0 제거
        removeFrontZero: function(str) {
            return this.toString(str).replace(/^-?0+(?!$)/, "");
        },

        // 천단위 콤마 추가
        addThousandComma: function(str) {
            return this.toString(str).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },

        // 뒤에 0 채우기
        fillBackZero: function(str, cnt) {
            var m = 0;

            for (m = 0; m < cnt; m++) {
                str = str + "0";
            }

            return str;
        },

        // 커서 위치 구하기
        getCursorPosition: function(val) {
            // 커서 시작 위치
            var startIndex = this.$refs.number.selectionStart;
            var strCnt = val.length;

            // 한글 체크
            var hangeul = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
            if (hangeul.test(val)) {
                startIndex = startIndex + 1;
            }

            // 앞에서 1번째 콤마 앞 숫자 삭제할 경우
            if (this.precision > 0) {
                var parts = val.split(".");

                if (parts[0].length % 4 == 0 && startIndex == 0) {
                    startIndex = 1;
                }
            } else {
                if (strCnt % 4 == 0 && startIndex == 0) {
                    startIndex = 1;
                }
            }

            return strCnt - startIndex;
        }
    },
    created: function() {
        // 최대 자릿수 기준값
        var maxDigits = this.max;

        if (this.toString(this.min).length > this.toString(this.max).length) {
            maxDigits = this.min;
        }

        // maxlength 설정
        if (this.precision > 0) {
            // 최대 실수값 구하기
            var maxFloat = this.getFloat(this.max, "change");

            // 최소 실수값 구하기
            var minFloat = this.getFloat(this.min, "change");

            // 최대 Integer 값
            var maxInt = maxFloat.integer;

            // min, max 비교
            if (minFloat.integer.length > maxFloat.integer.length) {
                maxInt = minFloat.integer;
            }

            // 최대길이
            if (this.masked === true) {
                this.maxLength = this.addThousandComma(maxInt).length + 1 + this.precision;
            } else {
                this.maxLength = maxInt.length + 1 + this.precision;
            }
        } else {
            // 최대 Integer 값
            var maxInt = this.max;

            // min, max 비교
            if (this.toString(this.min).length > this.toString(this.max).length) {
                maxInt = this.min;
            }

            // 최대길이
            if (this.masked === true) {
                this.maxLength = this.addThousandComma(maxInt).length;
            } else {
                this.maxLength = this.toString(maxInt).length;
            }
        }
    },
    mounted: function() {
    }
});
