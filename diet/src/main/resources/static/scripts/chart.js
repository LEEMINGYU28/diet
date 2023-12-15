var num = 0;
var numBar = 0;

var data = {
    labels: [
        "pink", "remaining"
    ],
    datasets: [
        {
            data: [num, 100 - num],
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

};

