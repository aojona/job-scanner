const font = new FontFace("Poppins", "https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap");
document.fonts.add(font);
font.load();
document.fonts.ready.then(() => {

    const ctx1 = document.getElementById('averageSalaryRangeChart');
    ctx1.height = 200;
    const ctx2 = document.getElementById('vacancyNumberCharts');
    ctx2.height = 200;

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
                borderWidth: 1.5
            }, {
                label: secondLabel,
                data: secondData,
                backgroundColor: [
                    'rgb(79,93,252, 0.05)'
                ],
                borderColor: [
                    '#4359ff'
                ],
                borderWidth: 1.5
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
                            weight: 'normal',
                            size: 18,
                        }
                    },
                    legend: {
                        labels: {
                            usePointStyle: true,
                            boxHeight: 9,
                            font: {
                                weight: 300
                            }
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
});