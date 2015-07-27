${"hallo"?string("hallo","servus")}
(start
	(sequence
	(inlineexpression ${
		(expression
			(expression
				(expression (string hallo)
				) ? string
			)
			'(' (expression (string hallo)) , (expression (string servus)) ')'
		) }
	)
	)
<EOF>
)