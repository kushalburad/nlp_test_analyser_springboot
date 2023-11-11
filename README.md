# nlp_test_analyser_springboot
NLP Test Analyzer - Spring Boot Application  Create a robust Spring Boot application that serves as an NLP test analyzer.

NLP Test Analyzer - Spring Boot Application

Create a robust Spring Boot application that serves as an NLP test analyzer. The application should accept input via an HTTP POST request to the endpoint http://localhost:8080/text_analyser. The input consists of text content and a CSV file.

Requirements:
Endpoint:
URL: http://localhost:8080/text_analyser
Method: POST
Input:
The HTTP request should contain:
Text content for analysis
CSV file for comparison
Text Analysis:
Utilize natural language processing (NLP) techniques to analyze the input text.
Extract and calculate the following metrics:
Nouns
Proper nouns
Verbs
Prepositions
Adjectives
Number of sentences
Word count
CSV Comparison:
Read the provided CSV file.
Compare the NLP analysis results with the data in the CSV file.
Output:
Display the results of the analysis, indicating any disparities between the NLP-derived metrics and the CSV data.
Implementation Guidelines:
Use Spring Boot and relevant dependencies (e.g., Spring Web) to build the application.
Implement a controller class (TextAnalyzerController) to handle the HTTP requests.
Create a service class (TextAnalyzerService) to encapsulate the text analysis and CSV comparison logic.
Design an appropriate model class (AnalysisResult) to represent the results of the analysis.
Ensure proper error handling, logging, and validation of input data.
