all: report.pdf

report.pdf:
	pdflatex report
	biber report
	pdflatex report
	pdflatex report

clean:
	rm -rf *~ *.aux *.bbl *.bcf *.blg *.log *.out *.run.xml report.pdf
