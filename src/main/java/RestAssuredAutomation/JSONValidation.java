package RestAssuredAutomation;

public class JSONValidation {

    public static String payload() {
        return """
        {
            "books": [
                {
                    "Book": "Clean Code",
                    "Price": 39.99,
                    "Pages": 464
                },
                {
                    "Book": "Effective Java",
                    "Price": 45.50,
                    "Pages": 416
                },
                {
                    "Book": "Design Patterns",
                    "Price": 55.00,
                    "Pages": 395
                },
                {
                    "Book": "Digital Signal Processing",
                    "Price": 65.00,
                    "Pages": 69
                }
            ]
        }
        """;
    }
}


