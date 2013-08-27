/*global Ext:false */
Ext.application({
    launch: function () {
        var chart = new Ext.chart.PolarChart({
            animate: true,
            interactions: ['rotate'],
            store: {
                fields: ['name', 'data1', 'data2', 'data3', 'data4', 'data5'],
                data: [{
                    'name': '力量',
                    'data1': 10,
                    'data2': 12,
                    'data3': 14,
                    'data4': 8,
                    'data5': 13
                }, {
                    'name': '速度',
                    'data1': 7,
                    'data2': 8,
                    'data3': 16,
                    'data4': 10,
                    'data5': 3
                }, {
                    'name': '技巧',
                    'data1': 5,
                    'data2': 2,
                    'data3': 14,
                    'data4': 12,
                    'data5': 7
                }, {
                    'name': '内线',
                    'data1': 2,
                    'data2': 14,
                    'data3': 6,
                    'data4': 1,
                    'data5': 23
                }, {
                    'name': '3分球',
                    'data1': 27,
                    'data2': 38,
                    'data3': 36,
                    'data4': 13,
                    'data5': 33
                }]
            },
            series: [{
                type: 'radar',
                xField: 'name',
                yField: 'data4',
                style: {
                    fillStyle: 'rgba(0, 0, 255, 0.1)',
                    strokeStyle: 'rgba(0, 0, 0, 0.8)',
                    lineWidth: 1
                }
            }],
            axes: [{
                type: 'numeric',
                position: 'radial',
                fields: 'data4',
                style: {
                    estStepSize: 10
                },
                grid: true
            }, {
                type: 'category',
                position: 'angular',
                fields: 'name',
                style: {
                    estStepSize: 1
                },
                grid: true
            }]
        });
        Ext.Viewport.setLayout('fit');
        Ext.Viewport.add(chart);
    }
});
