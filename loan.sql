--
-- Database: `loans`
--
CREATE DATABASE IF NOT EXISTS `loans` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `loans`;

-- --------------------------------------------------------

--
-- Table structure for table `banks`
--

CREATE TABLE `banks` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('Active','Suspended') NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banks`
--

INSERT INTO `banks` (`id`, `name`, `status`, `created`) VALUES
(1, 'Steward', 'Active', '2018-03-11 11:00:01');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `amount_required` double NOT NULL,
  `current_loan_amount` double NOT NULL,
  `current_monthly_payment` double NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('Applied','Mid-Level','Senior-Level','Approved','Denied') NOT NULL,
  `loan_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id`, `member_id`, `amount_required`, `current_loan_amount`, `current_monthly_payment`, `created`, `status`, `loan_type`) VALUES
(2, 1, 5, 0, 0, '2018-03-11 01:27:42', 'Approved', 2),
(3, 1, 5, 0, 0, '2018-03-18 05:33:11', 'Approved', 2),
(4, 1, 50, 10, 0, '2018-03-11 02:00:06', 'Approved', 2),
(5, 1, 50, 10, 0, '2018-03-18 06:11:18', 'Denied', 2),
(6, 1, 50, 110, 0, '2018-03-18 06:07:02', 'Mid-Level', 1),
(7, 1, 50, 110, 0, '2018-03-18 06:12:04', 'Approved', 1),
(8, 1, 25, 138, 36, '2018-03-11 11:07:04', 'Approved', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loan_types`
--

CREATE TABLE `loan_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `rate` float NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan_types`
--

INSERT INTO `loan_types` (`id`, `name`, `rate`, `created`) VALUES
(1, 'Advance Loan', 10, '2018-03-03 17:43:24'),
(2, 'Mid Term', 7, '2018-03-03 17:43:54');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `bank_account` varchar(255) NOT NULL,
  `savings_club_number` varchar(255) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `annual_salary` double NOT NULL,
  `monthly_net_salary` double NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `name`, `lastname`, `bank_id`, `bank_account`, `savings_club_number`, `job_title`, `grade_id`, `annual_salary`, `monthly_net_salary`, `mobile_number`, `address`, `created`) VALUES
(1, 'Delon', 'Savanhu', 1, '9005456768', '10009', 'Enginner', 2, 5000, 4800, '0773553310', '1234 Address', '2018-02-24 23:02:21'),
(2, 'Tendai', 'Tondoro', 1, '1234567', 'ZNA-1520789397', 'Grader', 1, 1234, 23, '0773553310', '1234 Northwood', '2018-03-11 17:30:41');

-- --------------------------------------------------------

--
-- Table structure for table `notications`
--

CREATE TABLE `notications` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `member_id`, `amount`, `created`) VALUES
(1, 1, 5, '2018-03-11 10:04:29'),
(2, 1, 67, '2018-03-11 10:05:30'),
(3, 2, 50, '2018-03-15 17:21:09');

-- --------------------------------------------------------

--
-- Table structure for table `salary_grades`
--

CREATE TABLE `salary_grades` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary_grades`
--

INSERT INTO `salary_grades` (`id`, `name`, `created`) VALUES
(1, 'Level 1', '2018-03-11 17:22:01');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `level` enum('Mid-Level','Senior-Level') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `phone`, `password`, `created`, `level`) VALUES
(1, 'Delon Savanhu', 'delon@afrodeb.com', '0773553310', 'gabby&kuda', NULL, 'Mid-Level'),
(2, 'Tendai Tondoro', 'delon@afrodeb.com', '0773553311', 'deedza12345', '2018-03-18 04:54:08', 'Senior-Level');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banks`
--
ALTER TABLE `banks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loan_types`
--
ALTER TABLE `loan_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notications`
--
ALTER TABLE `notications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary_grades`
--
ALTER TABLE `salary_grades`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banks`
--
ALTER TABLE `banks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `loan_types`
--
ALTER TABLE `loan_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `notications`
--
ALTER TABLE `notications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `salary_grades`
--
ALTER TABLE `salary_grades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
