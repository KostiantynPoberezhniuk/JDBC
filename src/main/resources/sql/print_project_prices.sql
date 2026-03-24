SELECT p.id AS name,
       DATEDIFF('MONTH', p.start_date, p.finish_date) * SUM(w.salary) AS price
FROM project p
JOIN project_worker pw ON p.id = pw.project_id
JOIN worker w ON pw.worker_id = w.id
GROUP BY name
ORDER BY price DESC;