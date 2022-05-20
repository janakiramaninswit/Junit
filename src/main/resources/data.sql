
CREATE TABLE IF NOT EXISTS `book` (
     `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
     `author` VARCHAR(255) NOT NULL,
     `category` VARCHAR(255) NOT NULL,
     `name` VARCHAR(255) DEFAULT NULL,
     `pages` INTEGER DEFAULT NULL,
	 `price` INTEGER DEFAULT NULL,
	 `publication` VARCHAR(255) DEFAULT NULL
);

INSERT INTO `book` (`id`, `author`, `category`, `name`, `pages`, `price`, `publication`) VALUES
(1, 'Santosh Kumar', 'Programming', 'Extensive Multithreading', 100, 50, 'Mcgraw Publication'),
(3, 'Suchitra', 'Programming', 'Java', 1000, 200, 'Mcgraw Publication'),
(5, 'Nitesh', 'Computer Programming', 'C++', 1500, 240, 'Rajput Publication');
