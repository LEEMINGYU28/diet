$(document).ready(function () {

    function setWaterLevelColor(btnClass, levelId) {
        var storedColor = sessionStorage.getItem(levelId + "-color");
        if (storedColor) {
            $(levelId).css("background-color", storedColor);
        }
    }

    function handleWaterButtonClick(btnClass, levelId) {
        $(btnClass).click(function () {
            var currentColor = $(levelId).css("background-color");

            if (currentColor === "rgba(52, 152, 219, 0.82)") {
                // 현재 색상이 설정된 상태이면 제거
                $(levelId).css("background-color", "");
                sessionStorage.removeItem(levelId + "-color");
            } else {
                // 현재 색상이 없는 상태이면 설정
                $(levelId).css("background-color", "#3498dbd1");
                sessionStorage.setItem(levelId + "-color", "#3498dbd1");
            }
        });
    }

    // 세션 스토리지에서 "water-color" 키의 값을 제거하는 함수
    function clearWaterSessionData() {
        sessionStorage.removeItem("water-color");
    }

    setWaterLevelColor(".water-btn", "#water-level");
    setWaterLevelColor(".water-btn2", "#water-level2");
    setWaterLevelColor(".water-btn3", "#water-level3");
    setWaterLevelColor(".water-btn4", "#water-level4");
    setWaterLevelColor(".water-btn5", "#water-level5");
    setWaterLevelColor(".water-btn6", "#water-level6");
    setWaterLevelColor(".water-btn7", "#water-level7");
    setWaterLevelColor(".water-btn8", "#water-level8");

    handleWaterButtonClick(".water-btn", "#water-level");
    handleWaterButtonClick(".water-btn2", "#water-level2");
    handleWaterButtonClick(".water-btn3", "#water-level3");
    handleWaterButtonClick(".water-btn4", "#water-level4");
    handleWaterButtonClick(".water-btn5", "#water-level5");
    handleWaterButtonClick(".water-btn6", "#water-level6");
    handleWaterButtonClick(".water-btn7", "#water-level7");
    handleWaterButtonClick(".water-btn8", "#water-level8");

    // 페이지 로드시 세션 스토리지 데이터 초기화
    clearWaterSessionData();
});
