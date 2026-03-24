SELECT id, DATEDIFF('MONTH', start_date, finish_date) AS months_count
FROM project
WHERE DATEDIFF('MONTH', start_date, finish_date) = (
    SELECT MAX(DATEDIFF('MONTH', start_date, finish_date)) FROM project
);