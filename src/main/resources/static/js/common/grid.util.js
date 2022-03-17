/**
 * File Name   : grid.util.js
 * Description : 그리드 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2019.01.22
 */

var GridUtil = {

    /**
     * 신규 행아이디 구하기
     * gridId : 그리드 아이디
     */
    getNewRowId: function(gridId) {
        var rowList = $("#" + gridId).jqGrid("getDataIDs");
        var rowCnt = rowList.length;
        var rowId = 0;
        var m = 0;

        for (m = 0; m < rowCnt; m++) {
            if (WebUtil.toInt(rowList[m]) > rowId) {
                rowId = WebUtil.toInt(rowList[m]);
            }
        }

        return rowId + 1;
    },

    /**
     * 컬럼 인덱스 구하기
     * gridId  : 그리드 아이디
     * colName : 컬럼명
     */
    getColIndex: function(gridId, colName) {
        var modelList = $("#" + gridId).jqGrid("getGridParam", "colModel");
        var modelCnt = modelList.length;
        var m = 0;
        var colIndex = 0;

        for (m = 0; m < modelCnt; m++) {
            if (modelList[m].name == colName) {
                colIndex = m;
                break;
            }
        }

        return colIndex;
    },

    /**
     * 체크된 목록 구하기
     * gridId : 그리드 아이디
     */
    getCheckedList: function(gridId) {
        var gridList = $("#" + gridId);
        var checkedList = gridList.jqGrid("getGridParam", "selarrrow");
        var checkedCnt = checkedList.length;
        var resultList = [];
        var m = 0;

        for (m = 0; m < checkedCnt; m++) {
            resultList.push(gridList.jqGrid("getRowData", checkedList[m]));
        }

        return resultList;
    },

    /**
     * 틀고정 행 높이 설정
     * grid : 그리드
     */
    setFixedRowHeight: function(grid) {
        if (grid.fbDiv != null && typeof grid.fbDiv !== "undefined") {
            var $rows = $("div > table.ui-jqgrid-btable > tbody > tr", grid.bDiv);

            $("table.ui-jqgrid-btable > tbody > tr", grid.fbDiv).each(function(idx) {
                if ($(this).hasClass("jqgrow")) {
                    var rowHight = $($rows[idx]).find("td:first-child").outerHeight();

                    $(this).find("td:first-child").innerHeight(rowHight);
                    $($rows[idx]).find("td:first-child").innerHeight(rowHight);
                }
            });

            $(grid.fbDiv).height(grid.bDiv.clientHeight);
        }
    },

    /**
     * 틀고정 스크롤 이벤트
     * grid : 그리드
     */
    onFixedScrollEvent: function(grid) {
        $(grid.fbDiv).on("mousewheel DOMMouseScroll", function(e) {
            var st = $(grid.bDiv).scrollTop();

            if (e.originalEvent.wheelDelta > 0 || e.originalEvent.detail < 0) {
                // up
                $(grid.bDiv).scrollTop(st - 25);
            } else {
                // down
                $(grid.bDiv).scrollTop(st + 25);
            }

            e.preventDefault();
        });
    },

    /**
     * 정렬 페이지 정보 수정
     * grid    : 그리드
     * resData : 응답 데이터
     */
    updateSortPager: function(gridId, resData) {
        var gridList = $("#" + gridId);

        if (gridList.jqGrid("getGridParam", "datatype") === "json") {
            var totCnt   = resData.data.totalCount;
            var currPage = resData.data.currentPage;
            var lastPage = Math.ceil(totCnt / resData.data.rowCount);

            // 페이지 정보
            gridList.data("totCnt", totCnt);
            gridList.data("currPage", currPage);
            gridList.data("lastPage", lastPage);

            // 페이지 설정
            gridList.jqGrid("setGridParam", {
                datatype: "local",
                records: totCnt,
                page: currPage,
                lastpage: lastPage
            });

            // 전체 건수 체크
            if (totCnt == 0) {
                Swal.alert(["결과가 존재하지 않습니다.", "info"]);
            }
        } else {
            // 페이지 설정
            gridList.jqGrid("setGridParam", {
                records: gridList.data("totCnt"),
                page: gridList.data("currPage"),
                lastpage: gridList.data("lastPage")
            });
        }

        // 페이지 정보 수정
        gridList[0].updatepager(false, true);
    },

    /**
     * 넓이 리사이즈
     * gridId   : 그리드 아이디
     * wraperId : 부모DIV 아이디
     */
    resizeWidth: function(gridId, wraperId) {
        $(window).bind("resize", function() {
            var gridList = $("#"+ gridId);

            // 그리드 넓이 설정
            gridList.jqGrid("setGridWidth", $("#" + wraperId).width(), true);

            // 헤더 넓이 설정
            if (WebUtil.isObject(gridList.jqGrid("getGridParam", "groupHeader"))) {
                // 데이터 첫번째 td 목록
                var firstTdList = $("#gview_" + gridId + " table.ui-jqgrid-btable tr.jqgfirstrow td");

                // 헤더 첫번째 th 넓이 설정
                $("#gview_" + gridId + " table.ui-jqgrid-htable tr.jqg-first-row-header th").each(function(idx) {
                    $(this).css("width", firstTdList.eq(idx).css("width"));
                });
            }
         }).trigger("resize");
    },

    /**
     * 헤더 리사이즈
     * gridId   : 그리드 아이디
     * wraperId : 부모DIV 아이디
     */
    resizeHeader: function(gridId, wraperId) {
        var gridList = $("#"+ gridId);

        // 그리드 넓이 설정
        gridList.jqGrid("setGridWidth", $("#" + wraperId).width(), true);

        // 헤더 넓이 설정
        if (gridList.jqGrid("getGridParam", "groupHeader") == null) {
            // 데이터 첫번째 td 목록
            var firstTdList = $("#gview_" + gridId + " table.ui-jqgrid-btable tr.jqgfirstrow td");

            // 헤더 첫번째 th 넓이 설정
            $("#gview_" + gridId + " table.ui-jqgrid-htable tr.ui-jqgrid-labels th").each(function(idx) {
                $(this).css("width", firstTdList.eq(idx).css("width"));
            });
        }
    },

    /**
     * 컬럼 헤더 리사이즈
     * gridId : 그리드 아이디
     * colIdx : 컬럼 인덱스
     * width  : 넓이
     */
    resizeColHeader: function(gridId, colIdx, width) {
        $("#gview_" + gridId + " table.ui-jqgrid-htable tr.jqg-first-row-header th").eq(colIdx).css("width", width);
    },

    /**
     * 정렬
     */
    sortable: function(gridId, actYn) {
        var gridList = $("#" + gridId);

        // 정렬 활성화
        if (actYn == true) {
            gridList.jqGrid("sortableRows", {
                disabled: false,
                start: function(e, ui) {
                    // tr hover 초기화
                    gridList.find("tr.ui-state-hover").each(function() {
                        $(this).removeClass("selected-row ui-state-hover");
                    });

                    // 아이템 hover 추가
                    ui.item.addClass("ui-state-hover");

                    // 빈 tr 높이 설정
                    ui.placeholder.css("height", ui.item.height() + "px");
                },
                stop: function(e, ui) {
                    // 아이템 hover 제거
                    ui.item.removeClass("ui-state-hover");
                }
            });

        // 정렬 비활성화
        } else {
            gridList.jqGrid("sortableRows", { disabled: true });
        }
    }

};
