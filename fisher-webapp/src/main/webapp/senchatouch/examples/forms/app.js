old	new	
...	...	@@ -63,16 +63,21 @@ Ext.application({
63	63	         144: 'resources/icons/icon@144.png'
64	64	     },
65	65	 
66		-   
	66	+    // Require the components we will be using in this example
	67	+    requires: [
	68	+        'Ext.form.*',
	69	+        'Ext.field.*',
	70	+        'Ext.Button',
	71	+        'Ext.Toolbar',
	72	+
	73	+        'Ext.data.Store'
	74	+    ],
67	75	 
68	76	     /**
69	77	      * The launch method of our application gets called when the application is good to launch.
70	78	      * In here, we are going to build the structure of our application and add it into the Viewport.
71	79	      */
72	80	     launch: function() {
73		-    	
74		-    	var chart=this.getChart();
75		-    
76	81	         // Get all the items for our form.
77	82	         var items = this.getFormItems(),
78	83	             config, form;
...	...	@@ -108,79 +113,6 @@ Ext.application({
108	113	         this.form = form;
109	114	     },
110	115	 
111		-    getChart:function(){
112		-    	return new Ext.chart.PolarChart({
113		-            animate: true,
114		-            interactions: ['rotate'],
115		-            store: {
116		-                fields: ['name', 'data1', 'data2', 'data3', 'data4', 'data5'],
117		-                data: [{
118		-                    'name': '力量',
119		-                    'data1': 10,
120		-                    'data2': 12,
121		-                    'data3': 14,
122		-                    'data4': 8,
123		-                    'data5': 13
124		-                }, {
125		-                    'name': '速度',
126		-                    'data1': 7,
127		-                    'data2': 8,
128		-                    'data3': 16,
129		-                    'data4': 10,
130		-                    'data5': 3
131		-                }, {
132		-                    'name': '技巧',
133		-                    'data1': 5,
134		-                    'data2': 2,
135		-                    'data3': 14,
136		-                    'data4': 12,
137		-                    'data5': 7
138		-                }, {
139		-                    'name': '内线',
140		-                    'data1': 2,
141		-                    'data2': 14,
142		-                    'data3': 6,
143		-                    'data4': 1,
144		-                    'data5': 23
145		-                }, {
146		-                    'name': '3分球',
147		-                    'data1': 27,
148		-                    'data2': 38,
149		-                    'data3': 36,
150		-                    'data4': 13,
151		-                    'data5': 33
152		-                }]
153		-            },
154		-            series: [{
155		-                type: 'radar',
156		-                xField: 'name',
157		-                yField: 'data4',
158		-                style: {
159		-                    fillStyle: 'rgba(0, 0, 255, 0.1)',
160		-                    strokeStyle: 'rgba(0, 0, 0, 0.8)',
161		-                    lineWidth: 1
162		-                }
163		-            }],
164		-            axes: [{
165		-                type: 'numeric',
166		-                position: 'radial',
167		-                fields: 'data4',
168		-                style: {
169		-                    estStepSize: 10
170		-                },
171		-                grid: true
172		-            }, {
173		-                type: 'category',
174		-                position: 'angular',
175		-                fields: 'name',
176		-                style: {
177		-                    estStepSize: 1
178		-                },
179		-                grid: true
180		-            }]
181		-        });
182		-    	
183		-    },
184	116	     /**
185	117	      * This method returns an array of all items we should add into the form panel we create above in our launch function.
186	118	      * We have created this function to simply make things cleaner and easier to read and understand. You could just put these items
...	...	@@ -191,225 +123,201 @@ Ext.application({
191	123	         return [
192	124	             {
193	125	                 xtype: 'fieldset',
194		-                title: '球员信息',
	126	+                title: 'Personal Info',
195	127	                 instructions: 'Please enter the information above.',
196	128	                 defaults: {
197	129	                     required: true
198	130	                 },
199	131	                 items: [
200		-                       
201	132	                     {
202	133	                         xtype: 'textfield',
203	134	                         name: 'name',
204		-                        label: '姓名',
	135	+                        label: 'Name',
205	136	                         autoCapitalize: false
206	137	                     },
207		-                   
208	138	                     {
209	139	                         xtype: 'passwordfield',
210	140	                         name: 'password',
211		-                        label: '密码'
	141	+                        label: 'Password'
	142	+                    },
	143	+                    {
	144	+                        xtype: 'textfield',
	145	+                        name: 'disabled',
	146	+                        label: 'Disabled',
	147	+                        disabled: true
212	148	                     },
213		-//                    {
214		-//                        xtype: 'textfield',
215		-//                        name: 'disabled',
216		-//                        label: 'Disabled',
217		-//                        disabled: true
218		-//                    },
219	149	                     {
220	150	                         xtype: 'emailfield',
221	151	                         name: 'email',
222		-                        label: '邮箱',
	152	+                        label: 'Email',
223	153	                         placeHolder: 'you@sencha.com'
224	154	                     },
225		-//                    {
226		-//                        xtype: 'urlfield',
227		-//                        name: 'url',
228		-//                        label: 'Url',
229		-//                        placeHolder: 'http://sencha.com'
230		-//                    },
231		-//                    {
232		-//                        xtype: 'checkboxfield',
233		-//                        name: 'cool',
234		-//                        label: 'Cool',
235		-//                        value: true
236		-//                    },
237		-//                    {
238		-//                        xtype: 'spinnerfield',
239		-//                        name: 'spinner',
240		-//                        label: 'Spinner'
241		-//                    },
	155	+                    {
	156	+                        xtype: 'urlfield',
	157	+                        name: 'url',
	158	+                        label: 'Url',
	159	+                        placeHolder: 'http://sencha.com'
	160	+                    },
	161	+                    {
	162	+                        xtype: 'checkboxfield',
	163	+                        name: 'cool',
	164	+                        label: 'Cool',
	165	+                        value: true
	166	+                    },
	167	+                    {
	168	+                        xtype: 'spinnerfield',
	169	+                        name: 'spinner',
	170	+                        label: 'Spinner'
	171	+                    },
242	172	                     {
243	173	                         xtype: 'selectfield',
244	174	                         name: 'rank',
245		-                        label: '位置',
	175	+                        label: 'Rank',
246	176	                         valueField: 'rank',
247	177	                         displayField: 'title',
248	178	                         store: {
249	179	                             data: [
250		-                                { rank: 'master', title: '中锋(C)'},
251		-                                { rank: 'padawan', title: '大前锋(PF)'},
252		-                                { rank: 'teacher', title: '小前锋(SF)'},
253		-                                { rank: 'aid', title: '得分后卫(SG)'},
254		-                                { rank: 'aid', title: '组织后卫(PG)'}
	180	+                                { rank: 'master', title: 'Master'},
	181	+                                { rank: 'padawan', title: 'Student'},
	182	+                                { rank: 'teacher', title: 'Instructor'},
	183	+                                { rank: 'aid', title: 'Assistant'}
255	184	                             ]
256	185	                         }
257	186	                     },
258	187	                     {
259		-                        xtype: 'numberfield',
260		-                        name: 'number',
261		-                        label: '身高(CM)'
262		-                    },
263		-                    {
264	188	                         xtype: 'datepickerfield',
265	189	                         name: 'date',
266		-                        label: '生日',
	190	+                        label: 'Start Date',
267	191	                         value: new Date(),
268	192	                         picker: {
269	193	                             yearFrom: 1980
270	194	                         }
271	195	                     },
272	196	                     {
	197	+                        xtype: 'hiddenfield',
	198	+                        name: 'secret',
	199	+                        value: 'false'
	200	+                    },
	201	+                    {
	202	+                        xtype: 'textareafield',
	203	+                        name: 'bio',
	204	+                        label: 'Bio',
	205	+                        maxRows: 10
	206	+                    },
	207	+                    {
	208	+                        xtype: 'sliderfield',
	209	+                        name: 'height',
	210	+                        label: 'Height'
	211	+                    },
	212	+                    {
	213	+                        xtype: 'togglefield',
	214	+                        name: 'enable',
	215	+                        label: 'Security Mode'
	216	+                    },
	217	+                    {
	218	+                        xtype: 'radiofield',
	219	+                        name: 'team',
	220	+                        label: 'Red Team',
	221	+                        value: 'redteam'
	222	+                    },
	223	+                    {
	224	+                        xtype: 'radiofield',
	225	+                        name: 'team',
	226	+                        label: 'Blue Team',
	227	+                        value: 'blueteam'
	228	+                    }
	229	+                ]
	230	+            },
	231	+            {
	232	+                xtype: 'fieldset',
	233	+                title: 'Favorite color',
	234	+                defaults: { xtype: 'radiofield' },
	235	+                items: [
	236	+                    { name: 'color', label: 'Red', value: 'red' },
	237	+                    { name: 'color', label: 'Green', checked: true, value: 'green'}
	238	+                ]
	239	+            },
	240	+            {
	241	+                xtype: 'fieldset',
	242	+                title: 'HTML5',
	243	+                items: [
	244	+                    {
273	245	                         xtype: 'numberfield',
274	246	                         name: 'number',
275		-                        label: '球员号码'
	247	+                        label: 'Number'
276	248	                     },
277	249	                     {
278		-                        xtype: 'selectfield',
279		-                        name: 'rank',
280		-                        label: '所属球队',
281		-                        valueField: 'rank',
282		-                        displayField: 'title',
283		-                        store: {
284		-                            data: [
285		-                                { rank: 'master', title: '8030'},
286		-                                { rank: 'padawan', title: 'CL'}
287		-                            ]
288		-                        }
	250	+                        xtype: 'emailfield',
	251	+                        name: 'email2',
	252	+                        label: 'Email',
	253	+                        clearIcon: true
289	254	                     },
290		-//                    {
291		-//                        xtype: 'hiddenfield',
292		-//                        name: 'secret',
293		-//                        value: 'false'
294		-//                    },
295	255	                     {
296		-                        xtype: 'textareafield',
297		-                        name: 'bio',
298		-                        label: '描述',
299		-                        maxRows: 10
	256	+                        xtype: 'urlfield',
	257	+                        name: 'url2',
	258	+                        label: 'URL',
	259	+                        clearIcon: true
	260	+                    }
	261	+                ]
	262	+            },
	263	+            {
	264	+                xtype: 'fieldset',
	265	+                title: 'Single Select',
	266	+                items: [
	267	+                    {
	268	+                        xtype: 'selectfield',
	269	+                        name: 'options',
	270	+                        options: [
	271	+                            {text: 'This is just a big select with text that is overflowing', value: '1'},
	272	+                            {text: 'Another item', value: '2'}
	273	+                        ]
	274	+                    }
	275	+                ]
	276	+            },
	277	+            {
	278	+                xtype: 'fieldset',
	279	+                title: 'Single Text',
	280	+                items: [
	281	+                    {
	282	+                        xtype: 'textfield',
	283	+                        name: 'single_text',
	284	+                        clearIcon: true
	285	+                    }
	286	+                ]
	287	+            },
	288	+            {
	289	+                xtype: 'fieldset',
	290	+                title: 'Single Toggle',
	291	+                items: [
	292	+                    {
	293	+                        xtype: 'togglefield',
	294	+                        name: 'single_toggle',
	295	+                        value: 1
	296	+                    }
	297	+                ]
	298	+            },
	299	+            {
	300	+                xtype: 'fieldset',
	301	+                title: 'Single Slider',
	302	+                items: [
	303	+                    {
	304	+                        xtype: 'sliderfield',
	305	+                        name: 'single_slider',
	306	+                        value: 60
	307	+                    }
	308	+                ]
	309	+            },
	310	+            {
	311	+                xtype: 'fieldset',
	312	+                title: 'Multiple Slider Thumbs',
	313	+                items: [
	314	+                    {
	315	+                        xtype: 'sliderfield',
	316	+                        name: 'multiple_slider',
	317	+                        values: [40, 90]
300	318	                     }
301		-//                    ,
302		-//                    
303		-//                    {
304		-//                        xtype: 'togglefield',
305		-//                        name: 'enable',
306		-//                        label: 'Security Mode'
307		-//                    },
308		-//                    {
309		-//                        xtype: 'radiofield',
310		-//                        name: 'team',
311		-//                        label: 'Red Team',
312		-//                        value: 'redteam'
313		-//                    },
314		-//                    {
315		-//                        xtype: 'radiofield',
316		-//                        name: 'team',
317		-//                        label: 'Blue Team',
318		-//                        value: 'blueteam'
319		-//                    }
320	319	                 ]
321	320	             },
322		-//            ,
323		-//            {
324		-//                xtype: 'fieldset',
325		-//                title: 'Favorite color',
326		-//                defaults: { xtype: 'radiofield' },
327		-//                items: [
328		-//                    { name: 'color', label: 'Red', value: 'red' },
329		-//                    { name: 'color', label: 'Green', checked: true, value: 'green'}
330		-//                ]
331		-//            },
332		-//            {
333		-//                xtype: 'fieldset',
334		-//                title: 'HTML5',
335		-//                items: [
336		-//                    {
337		-//                        xtype: 'numberfield',
338		-//                        name: 'number',
339		-//                        label: 'Number'
340		-//                    },
341		-//                    {
342		-//                        xtype: 'emailfield',
343		-//                        name: 'email2',
344		-//                        label: 'Email',
345		-//                        clearIcon: true
346		-//                    },
347		-//                    {
348		-//                        xtype: 'urlfield',
349		-//                        name: 'url2',
350		-//                        label: 'URL',
351		-//                        clearIcon: true
352		-//                    }
353		-//                ]
354		-//            },
355		-//            {
356		-//                xtype: 'fieldset',
357		-//                title: 'Single Select',
358		-//                items: [
359		-//                    {
360		-//                        xtype: 'selectfield',
361		-//                        name: 'options',
362		-//                        options: [
363		-//                            {text: 'This is just a big select with text that is overflowing', value: '1'},
364		-//                            {text: 'Another item', value: '2'}
365		-//                        ]
366		-//                    }
367		-//                ]
368		-//            },
369		-//            {
370		-//                xtype: 'fieldset',
371		-//                title: 'Single Text',
372		-//                items: [
373		-//                    {
374		-//                        xtype: 'textfield',
375		-//                        name: 'single_text',
376		-//                        clearIcon: true
377		-//                    }
378		-//                ]
379		-//            },
380		-//            {
381		-//                xtype: 'fieldset',
382		-//                title: 'Single Toggle',
383		-//                items: [
384		-//                    {
385		-//                        xtype: 'togglefield',
386		-//                        name: 'single_toggle',
387		-//                        value: 1
388		-//                    }
389		-//                ]
390		-//            },
391		-//            {
392		-//                xtype: 'fieldset',
393		-//                title: 'Single Slider',
394		-//                items: [
395		-//                    {
396		-//                        xtype: 'sliderfield',
397		-//                        name: 'single_slider',
398		-//                        value: 60
399		-//                    }
400		-//                ]
401		-//            },
402		-//            {
403		-//                xtype: 'fieldset',
404		-//                title: 'Multiple Slider Thumbs',
405		-//                items: [
406		-//                    {
407		-//                        xtype: 'sliderfield',
408		-//                        name: 'multiple_slider',
409		-//                        values: [40, 90]
410		-//                    }
411		-//                ]
412		-//            },
413	321	 
414	322	             // Create a docked bottom toolbar which will contain buttons to trigger various functions in our formpanel.
415	323	             {
