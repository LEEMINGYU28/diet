var num = 0; // 초기값 설정
var numBar = 0;

var data = {
    labels: [
        "pink", "remaining"
    ],
    datasets: [
        {
            data: [num, 100 - num], // 초기값 반영
            backgroundColor: [
                "#FF6384",
                "#DDDDDD"
            ],
            hoverBackgroundColor: [
                "#FF6384",
                "#DDDDDD"
            ]
        }]
};
var dataBar = {
    labels: ["pink", "remaining"],
    datasets: [
        {
            data: [numBar, 100 - numBar],
            backgroundColor: ["#36A2EB", "#DDDDDD"],
            hoverBackgroundColor: ["#36A2EB", "#DDDDDD"],
        },
    ],
};

window.onload = function () {
    var ctx8 = $('#chart8').get(0).getContext("2d");

    window.theChart8 = new Chart(ctx8, {
        type: 'doughnut',
        data: data,
        options: {
            responsive: true,
            legend: {
                display: false
            },
            elements: {
                center: {
                    text: num,
                    fontColor: "#FF0000",
                    fontSize: 20,
                    fontStyle: 'Helvetica',
                    sidePadding: 0

                }
            },
            maintainAspectRatio: false,
            cutoutPercentage: 90,
            animation: false,
            rotation: 1 * Math.PI,
            circumference: 1 * Math.PI
        }

    });

    var ctxBar = $('#horizontalBarChart0').get(0).getContext("2d");
    window.horizontalBarChart = new Chart(ctxBar, {
        type: 'horizontalBar',
        data: dataBar,
        options: {
            responsive: true,
            legend: { display: false },
            elements: {
                center: { text: numBar },
            },
            maintainAspectRatio: false,
            scales: {
                xAxes: [{
                    display: false,
                }],
                yAxes: [{
                    display: false,
                }],
            },
            animation: false,
        },
    });
    var ctxBar = $('#horizontalBarChart1').get(0).getContext("2d");
    window.horizontalBarChart = new Chart(ctxBar, {
        type: 'horizontalBar',
        data: dataBar,
        options: {
            responsive: true,
            legend: { display: false },
            elements: {
                center: { text: numBar },
            },
            maintainAspectRatio: false,
            scales: {
                xAxes: [{
                    display: false,
                }],
                yAxes: [{
                    display: false,
                }],
            },
            animation: false,
        },
    });
    var ctxBar = $('#horizontalBarChart2').get(0).getContext("2d");
    window.horizontalBarChart = new Chart(ctxBar, {
        type: 'horizontalBar',
        data: dataBar,
        options: {
            responsive: true,
            legend: { display: false },
            elements: {
                center: { text: numBar },
            },
            maintainAspectRatio: false,
            scales: {
                xAxes: [{
                    display: false,
                }],
                yAxes: [{
                    display: false,
                }],
            },
            animation: false,
        },
    });
};


// 사용자 입력에 따라 차트를 업데이트하는 함수
function updateChart() {
    var inputValue = parseInt($('#inputValue').val());

    if (!isNaN(inputValue) && inputValue >= 1 && inputValue <= 100) {
        numDonut += inputValue;
        numDonut = Math.min(numDonut, 100);
        numBar += inputValue;
        numBar = Math.min(numBar, 100);

        window.donutChart.data.datasets[0].data = [numDonut, 100 - numDonut];
        $('#centerText').text(numDonut);

        window.horizontalBarChart.data.datasets[0].data = [numBar, 100 - numBar];
        $('#centerText').text(numBar);

        window.donutChart.update();
        window.horizontalBarChart.update();
    } else {
        alert("1부터 100 사이의 정수를 입력하세요.");
    }
}