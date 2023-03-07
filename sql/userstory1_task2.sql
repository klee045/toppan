WITH loan_count_by_country AS (
	SELECT ppl.country_id, b.id, b.name, COUNT(b.id) AS "number_of_loans_in_country"
	FROM books b
	JOIN book_rents br ON br.book_id = b.id
	JOIN people ppl ON ppl.id = br.person_id
	GROUP BY ppl.country_id, b.id
	ORDER BY ppl.country_id, number_of_loans_in_country DESC
), top_3_books_by_country AS (
	SELECT *
	FROM (
		SELECT 
			*, 
			ROW_NUMBER() OVER (
				PARTITION BY loan_count_by_country.country_id
				ORDER BY loan_count_by_country.number_of_loans_in_country DESC
			) AS row_id FROM loan_count_by_country
	) AS A
	WHERE row_id < 4
), loan_count_by_book_and_country AS (
	SELECT
		b.id AS "book_id", b.name, 
		ppl.country_id,
		ppl.id AS "ppl_id",
		ppl.name AS "ppl_name",
		COUNT(ppl.id) AS "number_of_loans_by_person_in_country"
	FROM books b
	JOIN book_rents br ON br.book_id = b.id
	JOIN people ppl ON ppl.id = br.person_id
	GROUP BY b.id, ppl.country_id, ppl.id 
	ORDER BY b.id, ppl.country_id, ppl.id
), top_3_ppl_by_book_and_country AS (
	SELECT *
	FROM (
		SELECT 
			*, 
			ROW_NUMBER() OVER (
				PARTITION BY loan_count_by_book_and_country.book_id, loan_count_by_book_and_country.country_id
				ORDER BY loan_count_by_book_and_country.number_of_loans_by_person_in_country DESC
			) AS row_id FROM loan_count_by_book_and_country
	) AS A
	WHERE row_id < 4
)
SELECT 
	bbc.country_id,
	pbbc.book_id,
	bbc.name AS "book_name",
	bbc.number_of_loans_in_country,
	pbbc.ppl_name AS "top_3_borrower_in_country",
	pbbc.number_of_loans_by_person_in_country
FROM top_3_books_by_country bbc
JOIN top_3_ppl_by_book_and_country pbbc
ON bbc.country_id = pbbc.country_id AND bbc.id = pbbc.book_id 

