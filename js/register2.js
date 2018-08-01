/* *
 * 自定义JS文件:省市二级联查
 */
$(function() {
	//2.创建二维数组存储省份和城市
	var cities = new Array(34);
	cities[0] = new Array("北京","东城区", "西城区", "朝阳区", "海淀区");
	cities[1] = new Array("天津","河西区", "河东区", "和平区", "南开区");
	cities[2] = new Array("上海","静安区", "黄浦区", "徐汇区", "普陀区");
	cities[3] = new Array("重庆","渝中区", "江北区", "沙坪坝区", "九龙坡区");
	cities[4] = new Array("河北","保定市", "石家庄市", "唐山市", "秦皇岛市");
	cities[5] = new Array("山西","太原市", "大同市", "阳泉市", "长治市");
	cities[6] = new Array("辽宁","沈阳市", "大连市", "鞍山市", "抚顺市");
	cities[7] = new Array("吉林","长春市", "吉林市", "四平市", "辽源市");
	cities[8] = new Array("黑龙江","哈尔病市", "齐齐哈尔市", "鸡西市", "鹤岗市");
	cities[9] = new Array("江苏","南京市", "无锡市", "徐州市", "苏州市");
	cities[10] = new Array("浙江","杭州市", "宁波市", "温州市", "嘉兴市");
	cities[11] = new Array("安徽","合肥市", "淮南市", "马鞍山市", "淮北市");
	cities[12] = new Array("福建","福州市", "厦门市", "泉州市", "莆田市");
	cities[13] = new Array("江西","南昌市", "鹰潭市", "上饶市", "抚州市");
	cities[14] = new Array("山东","济南市", "威海市", "滨州市", "淄博市");
	cities[15] = new Array("河南","郑州市", "洛阳市", "开封市", "安阳市");
	cities[16] = new Array("湖北","武汉市", "黄石市", "十堰市", "宜昌市");
	cities[17] = new Array("湖南","长沙市", "株洲市", "湘潭市", "衡阳市");
	cities[18] = new Array("广东","广州市", "深圳市", "佛山市", "东莞市");
	cities[19] = new Array("海南","海口市", "三亚市", "三沙市", "五指山市");
	cities[20] = new Array("四川","成都市", "自贡市", "攀枝花市", "泸州市");
	cities[21] = new Array("贵州","贵阳市", "六盘水市", "遵义市", "安顺市");
	cities[22] = new Array("云南","昆明市", "大理市", "丽江市", "普洱市");
	cities[23] = new Array("陕西","西安市", "铜川市", "宝鸡市", "咸阳市");
	cities[24] = new Array("甘肃","兰州市", "嘉峪关市", "金昌市", "白银市");
	cities[25] = new Array("青海","西宁市", "海东市", "玉树市", "格尔木市");
	cities[26] = new Array("台湾","台北市", "新竹市", "台中市", "高雄市");
	cities[27] = new Array("内蒙古","呼和浩特市", "包头市", "乌海市", "赤峰市");
	cities[28] = new Array("广西","南宁市", "柳州市", "桂林市", "梧州市");
	cities[29] = new Array("西藏","拉萨市", "昌都市", "林芝市", "山南市");
	cities[30] = new Array("宁夏","银川市", "石嘴山市", "吴忠市", "固原市");
	cities[31] = new Array("新疆","乌鲁木齐市", "吐鲁番市", "哈密市", "昌吉市");
	cities[32] = new Array("香港","油尖旺区", "屯门区", "离岛区", "黄大仙区");
	cities[33] = new Array("澳门","圣方济各堂区", "望德堂区", "风顺堂区", "大堂区");
	$("#province").change(function() {
		//10.清除第二个下拉列表内容,否则上一次选择的内容会驻留在第二次下拉列表
		$("#city").empty();
		//1.获取用户选择的省份
		var val = this.value;
		var key;
		for(var i=0;i<cities.length;i++){
			if(val==cities[i][0]){
				key=i;//获得省份下标
			}
		}
		//3.遍历二维数组中的省份
		$.each(cities, function(i, n) {
			//4.判断用户选择省份和遍历的省份
			if (key == i) {
				//5.遍历该省份下的所有城市
				$.each(cities[i], function(j, m) {
					//alert(m);
					if(j>0){//去掉cities[i][0]节点
						//6.创建城市文本节点
						var textNode = document.createTextNode(m);
						//7.创建option元素节点
						var opEle = document.createElement("option");
						//8.将城市文本节点添加到option元素节点中去 
						//注意这里的方法是jq的方法使用时注意加上$()
						$(opEle).append(textNode);
						//9.将option元素节点追加到第二个下拉列表中
						$(opEle).appendTo($("#city"));	
					}
				});
			}
		});
	});
});