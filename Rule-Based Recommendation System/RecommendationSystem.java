import java.util.*;

public class RecommendationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Integer> views = new HashMap<>();
        Map<String, Integer> likes = new HashMap<>();
        Map<String, String> itemCategory = new HashMap<>();
        Map<String, Integer> categoryLikes = new HashMap<>();

        System.out.println("=== Rule-Based Recommendation System ===");

        // 1. Category Mapping
        System.out.println("\nEnter item categories:");
        System.out.println("Example: Phone:Electronics");
        System.out.println("Type END when finished.");

        while (true) {

            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("END")) {
                break;
            }

            String[] parts = input.split(":");

            if (parts.length == 2) {
                String item = parts[0].trim();
                String category = parts[1].trim();

                itemCategory.put(item, category);
            }
        }

        // 2. User Activity
        System.out.println("\nEnter user activities:");
        System.out.println("Example: VIEW:Phone or LIKE:Phone");
        System.out.println("Type END when finished.");

        while (true) {

            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("END")) {
                break;
            }

            String[] parts = input.split(":");

            if (parts.length != 2) {
                continue;
            }

            String action = parts[0].trim().toUpperCase();
            String item = parts[1].trim();

            // 3. View Counter
            if (action.equals("VIEW")) {

                views.put(
                    item,
                    views.getOrDefault(item, 0) + 1
                );
            }

            // 4. Like Counter + Category Likes
            else if (action.equals("LIKE")) {

                likes.put(
                    item,
                    likes.getOrDefault(item, 0) + 1
                );

                if (itemCategory.containsKey(item)) {

                    String category = itemCategory.get(item);

                    categoryLikes.put(
                        category,
                        categoryLikes.getOrDefault(category, 0) + 1
                    );
                }
            }
        }

        // 5. Find Top Liked Item
        String topLikedItem = "NONE";
        int maxLikes = 0;

        for (String item : likes.keySet()) {

            int currentLikes = likes.get(item);

            if (currentLikes > maxLikes) {

                maxLikes = currentLikes;
                topLikedItem = item;
            }
        }

        // 6. Find Recommended Category
        String recommendedCategory = "NONE";
        int maxCategoryLikes = 0;

        for (String category : categoryLikes.keySet()) {

            int currentCategoryLikes = categoryLikes.get(category);

            if (currentCategoryLikes > maxCategoryLikes) {

                maxCategoryLikes = currentCategoryLikes;
                recommendedCategory = category;
            }
        }

        // 7. Display Result
        System.out.println("\n========== RESULT ==========");

        System.out.println("\nItem Views:");

        for (String item : views.keySet()) {

            System.out.println(
                item + " = " + views.get(item)
            );
        }

        System.out.println(
            "\nTop Liked Item: " + topLikedItem
        );

        System.out.println(
            "Recommended Category: " + recommendedCategory
        );

        System.out.println("\n============================");

        sc.close();
    }
}