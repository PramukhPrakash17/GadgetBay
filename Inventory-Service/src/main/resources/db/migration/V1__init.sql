CREATE TABLE `t_inventory` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `skucode` varchar(255),
                            `quantity` int(11),
                            PRIMARY KEY (`id`)
);