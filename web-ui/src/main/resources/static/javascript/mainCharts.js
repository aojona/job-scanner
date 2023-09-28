const ctx1 = document.getElementById('averageSalaryRangeChart');
ctx1.height = 230;
const ctx2 = document.getElementById('vacancyNumberCharts');
ctx2.height = 230;

Chart.defaults.font.size = 15;
Chart.defaults.color = "#3245a6";

function createData(firstData, firstLabel, secondData, secondLabel) {
    return {
        labels: query,
        datasets: [{
            label: firstLabel,
            data: firstData,
            backgroundColor: [
                'rgba(230,115,153,0.05)',
            ],
            borderColor: [
                '#f23d82',
            ],
            borderWidth: 1
        }, {
            label: secondLabel,
            data: secondData,
            backgroundColor: [
                'rgba(103,114,230,0.05)'
            ],
            borderColor: [
                '#1e2eb9'
            ],
            borderWidth: 1
        },]
    };
}

function createConfig(data, text) {
    return {
        type: 'bar',
        data,
        options: {
            scales: {
                x: {
                    stacked: true,
                    grid: {
                        display: false
                    }
                },
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        maxRotation: 90,
                        minRotation: 90
                    }
                }
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
                    }
                }
            },
        }
    };
}

var data = createData(averageMinSalary, 'from', averageMaxSalary, 'to');
var config = createConfig(data, '        Average salary range');
const averageSalaryRangeChart = new Chart(ctx1, config);

data = createData(numberOfVacanciesWithSalary, 'with salary range', numberOfVacancies, 'all')
config = createConfig(data, '        Number of vacancies')
const vacancyNumberCharts = new Chart(ctx2, config);