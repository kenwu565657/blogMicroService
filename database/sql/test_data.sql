insert into app_user
(
   id, username, email,
   status
)
values
(
 'testing_id', 'testing_username', 'testing@testing.com',
 'ACTIVE'
);

insert into app_user_password
(
   id, app_user_id, hashed_password,
   status, failed_login_times, last_login_date_time,
   created_date_time
)
values
(
    'testing_id', 'testing_id', '$argon2id$v=19$m=15360,t=2,p=1$J3p4YzV/nSnexJ4CS1QPz6XyA3LSvEAOpQXwY4trdfo$fxB4yLjiV/GRxV48WIxO/eNQemJQ5tBcRrsqeqzmtcCH6PXrc6lV9M8xC5/uiQI/PTSu+ztFibtSZPJSUbpB4Q',
 'A', 0, '2020-06-22 19:10:25-07',
    '2020-06-22 19:10:25-07'
);