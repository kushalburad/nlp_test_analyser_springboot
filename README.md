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


IMAGES

INPUT IMAGE
<img width="1168" alt="Screenshot 2023-11-11 at 7 20 22 PM" src="https://github.com/kushalburad/nlp_test_analyser_springboot/assets/53971225/aaebafa9-5da8-49b8-a784-313efd7df5b3">

<img width="1168" alt="Screenshot 2023-11-11 at 7 20 10 PM" src="https://github.com/kushalburad/nlp_test_analyser_springboot/assets/53971225/997e2d26-cadc-4561-8e3d-55e728d02fdf">


INPUT FILE IMAGE (The format of input file should match the image, python code will generate the file in the same format (output_average))
<img width="1168" alt="Screenshot 2023-11-11 at 7 15 22 PM" src="https://github.com/kushalburad/nlp_test_analyser_springboot/assets/53971225/4d9bc34e-b01b-4710-8ad3-9d6f3d054192">



OUTPUT IMAGE
<img width="1168" alt="Screenshot 2023-11-11 at 7 20 31 PM" src="https://github.com/kushalburad/nlp_test_analyser_springboot/assets/53971225/130d07d0-e89f-44dc-8910-7bd10261c2f1">




