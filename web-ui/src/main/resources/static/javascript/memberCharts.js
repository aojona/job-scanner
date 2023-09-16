const ctx1 = document.getElementById('numberOfVacancies');
const ctx2 = document.getElementById('averageMinSalary');
const ctx3 = document.getElementById('averageMaxSalary');


Chart.defaults.font.family = "Poppins";
Chart.defaults.font.size = 16;
Chart.defaults.color = "#3c4fe0";

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
            layout: {
                padding: {
                    left: 50,
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
                    font: {
                        size: 18,
                    }
                },
                legend: {
                    labels: {
                        color: "#e03d7b",
                    }
                }
            }
        }
    };
}

var index = 0
var maxDateRange = allDate[0]
for (i = 1; i < allDate.length; i++) {
    if (allDate[1].length > maxDateRange) {
        index = i;
        maxDateRange = allDate[i];
    }
}

var colors = ["#ff0062", "#003dff", "#ff8000", "#f002ff"]
var backgroundColor = ["rgba(255,0,98,0.05)", "rgba(0,61,255,0.05)", "rgba(255,128,0,0.1)", "rgba(240,2,255,0.1)"]

var datasets = []
for (i = 0; i < query.length; i++) {
    datasets.push({label: query[i], data: allNumberOfVacancies[i], borderColor: colors[i], fill: true, tension: 0.1, borderWidth: 1.5, backgroundColor: backgroundColor[i]})
}

var data = createData(allDate[index], datasets);
var config = createConfig(data, 'Number of vacancies');
const numberOfVacanciesChart = new Chart(ctx1, config);

datasets = [];
for (i = 0; i < query.length; i++) {
    datasets.push({label: query[i], data: allAverageMinSalary[i], borderColor: colors[i], fill: true, tension: 0.1, borderWidth: 1.5, backgroundColor: backgroundColor[i]});
}
data = createData(allDate[index], datasets);
config = createConfig(data, 'Average minimum salary');
const averageMinSalaryChart = new Chart(ctx2, config);

datasets = [];
for (i = 0; i < query.length; i++) {
    datasets.push({label: query[i], data: allAverageMaxSalary[i], borderColor: colors[i], fill: true, tension: 0.1, borderWidth: 1.5, backgroundColor: backgroundColor[i]});
}
data = createData(allDate[index], datasets);
config = createConfig(data, 'Average maximum salary');
const averageMaxSalaryChart = new Chart(ctx3, config);