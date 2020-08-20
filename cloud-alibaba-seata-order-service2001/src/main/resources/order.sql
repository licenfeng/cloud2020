DROP TABLE
IF EXISTS `t_order`;

CREATE TABLE t_order (
	id BIGINT (11) NOT NULL auto_increment PRIMARY KEY,
	user_id BIGINT (11) DEFAULT NULL COMMENT '用户id',
	product_id BIGINT (11) DEFAULT NULL COMMENT '产品id',
	count INT (11) DEFAULT NULL COMMENT '数量',
	money DECIMAL (11, 0) DEFAULT NULL COMMENT '金额',
	STATUS INT (1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结'
) ENGINE = INNODB auto_increment = 7 DEFAULT CHARSET = utf8;

DROP TABLE
IF EXISTS `t_storage`;

CREATE TABLE t_storage (
	id BIGINT (11) NOT NULL auto_increment PRIMARY KEY,
	product_id BIGINT (11) DEFAULT NULL COMMENT '产品id',
	total BIGINT (11) DEFAULT NULL COMMENT '总库存',
	used INT (11) DEFAULT NULL COMMENT '已用库存',
	residue INT (11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE = INNODB auto_increment = 2 DEFAULT CHARSET = utf8;

DROP TABLE
IF EXISTS `t_account`;

CREATE TABLE t_account (
	id BIGINT (11) NOT NULL auto_increment PRIMARY KEY,
	user_id BIGINT (11) DEFAULT NULL COMMENT '用户id',
	total BIGINT (11) DEFAULT NULL COMMENT '总额度',
	used INT (11) DEFAULT NULL COMMENT '已用额度',
	residue INT (11) DEFAULT NULL COMMENT '剩余可用额度'
) ENGINE = INNODB auto_increment = 2 DEFAULT CHARSET = utf8;

INSERT INTO t_storage
VALUE
	(1, 1, 100, 0, 100);

INSERT INTO t_account
VALUES
	(1, 1, 1000, 0, 1000);