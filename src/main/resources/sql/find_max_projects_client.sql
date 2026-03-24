SELECT c.name, COUNT(p.id) AS project_count
FROM client c
JOIN project p ON c.id = p.client_id
GROUP BY c.id, c.name
ORDER BY project_count DESC
FETCH FIRST 1 ROW ONLY;