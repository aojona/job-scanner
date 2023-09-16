const ctx1 = document.getElementById('averageSalaryRangeChart');
const ctx2 = document.getElementById('vacancyNumberCharts');

Chart.defaults.font.family = "Poppins";
Chart.defaults.font.size = 16;
Chart.defaults.color = "#3c4fe0";

function createData(firstData, firstLabel, secondData, secondLabel) {
    return {
        labels: query,
        datasets: [{
            label: firstLabel,
            data: firstData,
            backgroundColor: [
                'rgba(255,120,171,0.05)',
            ],
            borderColor: [
                '#ff69a4',
            ],
            borderWidth: 1
        },{
            label: secondLabel,
            data: secondData,
            backgroundColor: [
                'rgb(79,93,252, 0.05)'
            ],
            borderColor: [
                '#4359ff'
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
                    },
                }
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

var data = createData(averageMinSalary, 'from', averageMaxSalary, 'to');
var config = createConfig(data, 'Average salary range for random subscriptions');
const averageSalaryRangeChart = new Chart(ctx1, config);

data = createData(numberOfVacanciesWithSalary, 'with salary range', numberOfVacancies, 'all')
config = createConfig(data, 'Vacancy number for random subscriptions')
const vacancyNumberCharts = new Chart(ctx2, config);