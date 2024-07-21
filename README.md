# just-recipeez
This is a simple app for organizing recipes using Kotlin and Jetpack Compose in Android.

## Objective
This app should provide a user the means to collect, organize, and search recipes with ease.  It should assist with nutritional goals, meal planning, and grocery budgeting.  It should introduce a variety of flavors and seasonal dishes to keep cooking more interesting and satisfying.

## Proposed Features
These are the target features for this app.

### Categories and Tags
User should be able to sort by custom **categories** and **tags**.  **Categories** might include *breakfast*, *lunch*, *snack* and **tags** might include *vegan*, *holiday*, or *quick meals*.  This could be coupled to [searches and filters](#searches-and-filters) to improve sorting.  This might best be implemented using Compose `LazyColumn` or `LazyGrid` and `Room` for data storage.

### Searches and Filters
User should be able to use **searches** and **filters** for recipes.  Search or filter criteria might include *ingredients*, *recipe name*, or *dietary restrictions*.  This could also be coupled to [categories and tags](#categories-and-tags).  This might be implemented using Compose `state` management and Kotlin data handling.

### Ratings and Reviews
User should be able to apply **ratings** and **reviews**.  This would be more relavent for access to recipes online or through social media and other forums.  This might be implemented using Compose `Slider` or `RatingsBar` while managing data with `Room`.

### Shopping Lists
User should be able to generate a **shopping list**.  Such a list could include `checkbox` components or `strike-through` text for items purchased or already on hand.  This could be coupled to [meal planning](#meal-planning) and [batches and notes](#batches-and-notes).  This might be implemented with Componse `UI` and Kotlin `collection` handling.

### Meal Planning
User should be able to manage **meal planning** through a weekly or monthly calendar.  This could be coupled to [shopping lists](#shopping-lists), [batches and notes](#batches-and-notes) and [nutritional information](#nutritional-information) to assist in budgeting or meeting nutritional goals.  This might be implemented using Compose `CalenderView` and `Room` for data storage.

### Imports and Scans
User should be able source recipes through **imports and scans** such as posting links to a *webpage* or using the device camera to image *handwritten* recipes.  This might be a more complex implementation through a web scraping `API`, integrating with a machine learning or `OCR` library.

### Timers
User should be able to set multiple **timers**.  These could be named and could be an improvement over the *single* timer found in most household kitchens.  This might be implemented easily through vanilla Kotlin.

### Nutritional Information
User should be provided detailed **nutritional information**.  This could be coupled to [meal planning](#meal-planning) as a means to meet certain nutritional goals.  This might be implemented using a third-party or public `API`.

### Favorites and Bookmarks
User should be able to apply **favorites** or **bookmarks** for ease of access.  This might be easily implemented with Compose `UI` and `Room` for data management or `SharedPreferences`.

### Batches and Notes
User should be able to select **batches** to modify serving sizes and personalize by adding **notes**.  This could be coupled to [shopping lists](#shopping-lists) and [meal planning](#meal-planning).  This might be implented using Compose `input` components and persistent storage using `Room`.

## Summary
This app aims to strike a balance between being feature rich, yet simple and approachable enough for anybody to use and benefit.

As always, feedback is welcome.  Suggestions or comments can be sent to me directly at [h.yavitz@gmail.com](mailto:h.yavitz@gmail.com).
