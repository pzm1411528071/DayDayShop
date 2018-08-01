/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50154
Source Host           : localhost:3306
Source Database       : shopdb

Target Server Type    : MYSQL
Target Server Version : 50154
File Encoding         : 65001

Date: 2018-06-23 08:02:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ausername` varchar(50) CHARACTER SET utf8 NOT NULL,
  `apassword` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ausername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('蛋蛋男爵', '123');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '家具家居');
INSERT INTO `category` VALUES ('4', '男装女装');
INSERT INTO `category` VALUES ('5', '水果蔬菜');
INSERT INTO `category` VALUES ('6', '汽车配件');
INSERT INTO `category` VALUES ('7', '运动户外');
INSERT INTO `category` VALUES ('8', '海外代购');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(50) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `oid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_0001` (`pid`),
  KEY `fk_0002` (`oid`),
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('04c4f378-7cbd-4d73-86ea-aabcb4b18f9b', '1', '4087', '16', '5f6e0784-825c-438e-9761-8b95431707b5');
INSERT INTO `orderitem` VALUES ('0d24c815-c370-4949-a230-a94eeac47f9d', '1', '1999', '22', '6df5c588-ea88-45da-96c3-669951704fbc');
INSERT INTO `orderitem` VALUES ('11288954-4b4f-4747-af16-f8feeefc6be3', '1', '10288', '39', '36c4f0c3-ec4b-4703-84a4-5655d05c81b2');
INSERT INTO `orderitem` VALUES ('15318246-a4d2-4eff-937f-59208da6acc6', '1', '1999', '4', '935c3a11-b62d-4882-be07-87bd6f5588a3');
INSERT INTO `orderitem` VALUES ('31943379-48d6-4c5c-a42f-cbf64d2c7193', '1', '5499', '41', '935c3a11-b62d-4882-be07-87bd6f5588a3');
INSERT INTO `orderitem` VALUES ('3f1c2368-e686-47b7-a8bf-5757c84d3ad7', '1', '4288', '15', 'fda63dd9-7802-4711-95aa-86d21bc86992');
INSERT INTO `orderitem` VALUES ('5de6d283-fd64-4696-8a0c-3a679616fea9', '1', '6599', '40', '1b1bce85-0e33-467f-92b5-ba9ab46edd5b');
INSERT INTO `orderitem` VALUES ('6dff1eea-5650-45be-b541-99c8538b6eab', '1', '5499', '41', '963436cc-83cd-43dc-8c76-6d4b95feacc9');
INSERT INTO `orderitem` VALUES ('a3aafa29-58f5-4399-aafc-db951a409db1', '1', '6599', '40', '74b383b9-81d5-45b4-8f21-ebdde68d31e4');
INSERT INTO `orderitem` VALUES ('a84f1d95-067c-41be-b003-991c715271fb', '1', '1999', '4', '933966a9-08e3-4b30-9eb7-fdb4560dac68');
INSERT INTO `orderitem` VALUES ('c9e671c7-7d2d-4541-a803-b4c09f9639b6', '1', '10288', '39', '0d01cab1-39e3-431b-b4ab-12131bb9d077');
INSERT INTO `orderitem` VALUES ('e6bc0bcf-4bd1-4d9b-b511-c9ca32fe47c1', '1', '3999', '17', 'c7b63930-2e45-4c30-9838-9874fa2abfa1');
INSERT INTO `orderitem` VALUES ('ea7d4f9b-7b08-472a-9ecc-47250f509aea', '1', '4288', '15', '5f6e0784-825c-438e-9761-8b95431707b5');
INSERT INTO `orderitem` VALUES ('ea835b34-e1fd-4080-a444-3636aa0b3642', '1', '4288', '15', '19522859-6ed7-4a57-b861-3604b7e4a592');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(50) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `uid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0d01cab1-39e3-431b-b4ab-12131bb9d077', '2018-06-14 20:52:10', '10288', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('19522859-6ed7-4a57-b861-3604b7e4a592', '2018-06-14 22:11:06', '4288', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('1b1bce85-0e33-467f-92b5-ba9ab46edd5b', '2018-06-18 12:29:49', '6599', '0', null, null, null, '7ac703d6-de34-4ea9-83b2-ce1acfe45cb4');
INSERT INTO `orders` VALUES ('36c4f0c3-ec4b-4703-84a4-5655d05c81b2', '2018-06-15 09:47:10', '10288', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('5f6e0784-825c-438e-9761-8b95431707b5', '2018-06-21 12:14:21', '8375', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('6df5c588-ea88-45da-96c3-669951704fbc', '2018-06-16 12:12:03', '1999', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('74b383b9-81d5-45b4-8f21-ebdde68d31e4', '2018-06-18 12:14:06', '6599', '0', '江西', '大妈', '15797768089', '7ac703d6-de34-4ea9-83b2-ce1acfe45cb4');
INSERT INTO `orders` VALUES ('933966a9-08e3-4b30-9eb7-fdb4560dac68', '2018-06-17 17:10:16', '1999', '1', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('935c3a11-b62d-4882-be07-87bd6f5588a3', '2018-06-19 08:46:27', '7498', '1', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('963436cc-83cd-43dc-8c76-6d4b95feacc9', '2018-06-14 22:24:44', '5499', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');
INSERT INTO `orders` VALUES ('c7b63930-2e45-4c30-9838-9874fa2abfa1', '2018-06-18 12:31:33', '3999', '0', '江西南昌', '大妈', '15797768089', '7ac703d6-de34-4ea9-83b2-ce1acfe45cb4');
INSERT INTO `orders` VALUES ('fda63dd9-7802-4711-95aa-86d21bc86992', '2018-06-17 01:20:28', '4288', '0', '江西南昌', '大福', '15797768089', 'ce509c30-80e6-48c6-b5a4-c781b5f96404');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(50) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pflag` int(11) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sfk_0001` (`cid`),
  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('14', 'vivo X5M', '1899', '1799', 'images/ele_equipment/phone/ph0001.jpg', '2015-11-02', '0', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', '0', '1');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', '4399', '4288', 'images/ele_equipment/phone/ph0002.jpg', '2015-11-02', '1', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', '0', '1');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', '4200', '4087', 'images/ele_equipment/phone/ph0003.jpg', '2015-11-03', '0', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', '0', '1');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', '4099', '3999', 'images/ele_equipment/phone/ph0004.jpg', '2015-11-02', '0', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', '0', '1');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', '3599', '3499', 'images/ele_equipment/phone/ph0005.jpg', '2015-11-02', '0', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', '0', '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', '1599', '1469', 'images/ele_equipment/phone/ph0006.jpg', '2015-11-02', '1', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', '0', '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', '2899', '2699', 'images/ele_equipment/phone/ph0007.jpg', '2015-11-05', '1', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', '649', '549', 'images/ele_equipment/phone/ph0008.jpg', '2015-11-02', '0', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', '0', '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', '1099', '999', 'images/ele_equipment/phone/ph0009.jpg', '2015-11-02', '0', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', '0', '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', '2099', '1999', 'images/ele_equipment/phone/ph00010.jpg', '2015-11-02', '1', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', '0', '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', '1799', '1699', 'images/ele_equipment/phone/ph00011.jpg', '2015-11-09', '1', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', '0', '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', '3988', '3888', 'images/ele_equipment/phone/ph00012.jpg', '2015-11-02', '1', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', '0', '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', '5188', '4988', 'images/ele_equipment/phone/ph00013.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', '6388', '6088', 'images/ele_equipment/phone/ph00014.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', '5588', '5388', 'images/ele_equipment/phone/ph00015.jpg', '2015-11-02', '0', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', '0', '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', '5999', '5888', 'images/ele_equipment/phone/ph00016.jpg', '2015-11-02', '0', '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', '0', '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', '3018', '2978', 'images/ele_equipment/phone/ph00017.jpg', '2015-11-02', '0', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', '0', '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', '1099', '999', 'images/ele_equipment/phone/ph00019.jpg', '2015-11-02', '0', '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', '0', '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', '2399', '2299', 'images/ele_equipment/pc/pc00020.jpg', '2018-06-17', '1', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', '0', '2');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', '11299', '10288', 'images/ele_equipment/pc/pc0001.jpg', '2015-11-02', '0', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', '0', '2');
INSERT INTO `product` VALUES ('4', '联想 P1', '2199', '1999', 'images/ele_equipment/pc/pc0002.jpg', '2015-11-02', '1', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', '0', '2');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', '6799', '6599', 'images/ele_equipment/pc/pc0003.jpg', '2015-11-02', '0', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', '0', '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', '5699', '5499', 'images/ele_equipment/pc/pc0004.jpg', '2015-11-02', '1', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', '0', '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', '6199', '5999', 'images/ele_equipment/pc/pc0005.jpg', '2015-11-02', '0', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', '0', '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', '5699', '5499', 'images/ele_equipment/pc/pc0006.jpg', '2015-11-02', '0', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', '0', '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', '3199', '3099', 'images/ele_equipment/pc/pc0007.jpg', '2015-11-02', '0', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', '0', '2');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', '10999', '9899', 'images/ele_equipment/pc/pc0008.jpg', '2015-11-02', '0', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', '0', '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '3299', '3199', 'images/ele_equipment/pc/pc0009.jpg', '2015-11-02', '0', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', '0', '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', '5099', '4899', 'images/ele_equipment/pc/pc00010.jpg', '2015-11-11', '0', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', '0', '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', '2088', '1888', 'images/ele_equipment/pc/pc00011.jpg', '2015-11-02', '0', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', '0', '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', '1399', '1299', 'images/ele_equipment/pc/pc00012.jpg', '2015-11-02', '0', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', '0', '2');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', '1799', '1699', 'images/ele_equipment/pc/pc00013.jpg', '2015-11-01', '0', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', '0', '2');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', '2399', '2299', 'images/ele_equipment/pc/pc00019.jpg', '2018-06-17', '0', '（9.7英寸 16G WLAN 机型 银色）', '0', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(50) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('24a19a44-b939-4c8a-8a21-20a28e5acf5c', '蛋蛋男爵', '177054dc02a7f32bbadf7666d23a455a', '15797768089@163.com', '李四', '河南', '郑州市', '男', '2018-06-12', '15159279268', '1', '2d9e90d3-6071-4312-949e-7ad65444ccbc');
INSERT INTO `user` VALUES ('3f9cf597-ad80-4ea7-a477-e605fc73328f', '卡二', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '张三', '天津', '河西区', '男', '2018-06-07', '15797768089', '1', 'be9e812a-b615-4824-bbd4-3e71896bee2e');
INSERT INTO `user` VALUES ('48b52a10-9ca1-4dd1-812c-9175a76c1a84', '欧文', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '陈六', '江西', '南昌市', '男', '2018-06-13', '15797768089', '0', '902983db-432a-49d3-9761-eeb868108296');
INSERT INTO `user` VALUES ('7ac703d6-de34-4ea9-83b2-ce1acfe45cb4', '大妈', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '张三', '湖北', '武汉市', '男', '2018-06-03', '15797768089', '1', '38c4caaf-c92c-4294-a688-b29dfe351334');
INSERT INTO `user` VALUES ('8fcaccf6-4a03-4634-b838-0753114023fe', '红发', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '李四', '江西', '南昌市', '男', '2018-06-02', '15797768089', '1', '30fa66e7-b5da-4f51-ba9f-0d292df08f71');
INSERT INTO `user` VALUES ('96975e7d-60eb-400b-b790-140cf2f50758', '张飒', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '李四', '湖北', '武汉市', '男', '2018-06-03', '15797768089', '1', '09c22b17-cc40-4f56-bf85-c30c898c47c2');
INSERT INTO `user` VALUES ('ce509c30-80e6-48c6-b5a4-c781b5f96404', '大福', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '王五', '浙江', '杭州市', '男', '2018-06-15', '15797768089', '1', '57b0326e-bc41-4260-ab46-3ecbf4e8c1c4');
INSERT INTO `user` VALUES ('d6ed11bb-f09b-45a5-9ed0-53d7a0317946', '凯多', 'e9c699344a8104165cec2dba609cd25f', '15797768089@163.com', '张三', '山东', '济南市', '男', '2018-06-08', '15797768089', '1', '31e25b87-cafb-4a5f-a2de-29a609bfe5a1');
