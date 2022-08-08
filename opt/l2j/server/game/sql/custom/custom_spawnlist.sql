CREATE TABLE IF NOT EXISTS `custom_spawnlist` (
  `location` varchar(40) NOT NULL DEFAULT '',
  `count` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `npc_templateid` int(10) unsigned NOT NULL DEFAULT '0',
  `locx` mediumint(8) NOT NULL DEFAULT '0',
  `locy` mediumint(6) NOT NULL DEFAULT '0',
  `locz` mediumint(6) NOT NULL DEFAULT '0',
  `randomx` mediumint(6) NOT NULL DEFAULT '0',
  `randomy` mediumint(6) NOT NULL DEFAULT '0',
  `heading` mediumint(6) NOT NULL DEFAULT '0',
  `respawn_delay` mediumint(5) NOT NULL DEFAULT '0',
  `respawn_random` mediumint(5) NOT NULL DEFAULT '0',
  `loc_id` int(9) NOT NULL DEFAULT '0',
  `periodOfDay` tinyint(1) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `custom_spawnlist` VALUES
("unset", 1, 900105, 82272, 148604, -3468, 0, 0, 62980, 60, 0, 0, 0);
