const ctx1 = document.getElementById('numberOfVacancies');
ctx1.height = 250;
const ctx2 = document.getElementById('averageMinSalary');
ctx2.height = 250;
const ctx3 = document.getElementById('averageMaxSalary');
ctx3.height = 250;

Chart.defaults.font.size = 15;
Chart.defaults.color = "#3245a6";

function createData(date, datasets) {
    return {
        labels: date,
        datasets: datasets
    };
}

function createConfig(data, text) {
    return {
        type: 'line',
        data,
        options: {
            responsive: true,
            layout: {
                padding: {
                    left: 15,
                    right: 50
                }
            },
            scales: {
                x: {
                    stacked: true,
                    grid: {
                        display: false
                    },
                    reverse: true
                },
            },
            plugins: {
                title: {
                    display: true,
                    text: text,
                    color: "#2131af",
                    font: {
                        weight: 500,
                        size: 18,
                    }
                },
                legend: {
                    labels: {
                        usePointStyle: true,
                        boxHeight: 9
                    },
                }
            },
        }
    };
}

var index = 0
var maxDateRange = allDate[0].length
for (i = 1; i < allDate.length; i++) {
    if (allDate[i].length > maxDateRange) {
        index = i;
        maxDateRange = allDate[i];
    }
}

var colors = ["#ff2679", "#2659ff", "#f57a00", "#0086d9"]
var backgroundColor = ["rgba(255,38,121,0.03)", "rgba(38,89,255,0.03)", "rgba(245,122,0,0.03)", "rgba(0,134,217,0.03)"]

var datasets = []
for (i = 0; i < query.length; i++) {
    datasets.push({
        label: query[i],
        data: allNumberOfVacancies[i],
        borderColor: colors[i],
        fill: true,
        tension: 0.1,
        borderWidth: 1,
        backgroundColor: backgroundColor[i]
    })
}

var data = createData(allDate[index], datasets);
var config = createConfig(data, '         Number of vacancies');
const numberOfVacanciesChart = new Chart(ctx1, config);

datasets = [];
for (i = 0; i < query.length; i++) {
    datasets.push({
        label: query[i],
        data: allAverageMinSalary[i],
        borderColor: colors[i],
        fill: true,
        tension: 0.1,
        borderWidth: 1,
        backgroundColor: backgroundColor[i]
    });
}
data = createData(allDate[index], datasets);
config = createConfig(data, '         Average minimum salary');
const averageMinSalaryChart = new Chart(ctx2, config);

datasets = [];
for (i = 0; i < query.length; i++) {
    datasets.push({
        label: query[i],
        data: allAverageMaxSalary[i],
        borderColor: colors[i],
        fill: true,
        tension: 0.1,
        borderWidth: 1,
        backgroundColor: backgroundColor[i]
    });
}
data = createData(allDate[index], datasets);
config = createConfig(data, '         Average maximum salary');
const averageMaxSalaryChart = new Chart(ctx3, config);