# GreenSnack 🍏🌍

GreenSnack is a food and meal planner app developed during a 24-hour Hackathon focused on Green Cities and raising environmental awareness. The app creates personalized meal plans using user-specific data to minimize CO2 emissions and food waste, helping users make sustainable and cost-effective food choices.

## 🌟 Features

### Personalized Meal Plans
Generate a meal plan tailored to your personal parameters:
- **Age**
- **Height**
- **Weight**
- **Weekly Budget**

### Eco-Friendly Food Choices
Leverages data on food items that produce the least CO2 emissions, providing you with meals that have a lower carbon footprint.

### Recipe Generation
Uses the OpenAI API to generate creative and personalized recipes that align with the user's sustainability goals.

### Grocery List Creation
Automatically generates a grocery list based on your meal plan, reducing impulsive buying and food waste.

### Personalized Insights
Access personalized statistics and graphs on:
- **CO2 Savings:** The amount of CO2 saved per kg by following the meal plan.
- **Food Waste Reduction:** Track how much food waste is reduced in kilograms.
- **Money Savings:** Visualize your financial savings from planning ahead.

## 📱 Video Preview

https://drive.google.com/file/d/1ucvPvDqEjRPl0i3D7g9A084iroOQOwPq/view?usp=sharing


## 💡 Motivation

GreenSnack was built during a Hackathon centered around the theme of Green Cities and environmental awareness. The goal is to help individuals make conscious food choices that have a positive impact on the planet by promoting low carbon footprint meals and reducing food waste.

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:
- **Android Studio** (latest version)
- **Kotlin** (version used in the project)
- **OpenAI API Key** (for recipe generation)

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/GreenSnack.git
    cd GreenSnack
    ```

2. **Open in Android Studio:**
    - Launch Android Studio.
    - Click on `File > Open` and select the project directory.
    - Sync the project with Gradle files.

3. **Configure OpenAI API Key:**
    - Add your OpenAI API key in the appropriate file (e.g., `secrets.properties`, an `.env` file or directly to the `Home` fragment) to enable recipe generation.

4. **Run the app:**
    - Connect your Android device or start an emulator.
    - Click the Run button in Android Studio.

## 🌍 How It Works

1. **Input Personal Data:** Users provide their age, height, weight, and weekly budget.
2. **Generate Meal Plan:** GreenSnack uses the OpenAI API to create a personalized meal plan based on your inputs and food items with low CO2 emissions.
3. **Grocery List Creation:** The app generates a grocery list to streamline shopping and reduce impulsive purchases.
4. **Track Savings:** Users can view detailed stats and graphs on CO2 savings, food waste reduction, and financial benefits.

## 🎨 Tech Stack

- **Platform:** Android
- **Language:** Kotlin
- **API:** OpenAI API (for recipe generation)
- **Graphical Data:** MPAndroidChart v3.1.0

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
