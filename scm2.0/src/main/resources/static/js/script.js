console.log("Script loaded successfully");

let currentTheme = getTheme();
applyTheme(currentTheme); // apply on first load

document.addEventListener("DOMContentLoaded", () => {
    const themeButton = document.querySelector("#theme_change_button");

    if (themeButton) {
        updateButtonLabel(themeButton, currentTheme);

        themeButton.addEventListener("click", () => {
            const oldTheme = currentTheme;
            currentTheme = currentTheme === "dark" ? "light" : "dark";
            setTheme(currentTheme);
            document.documentElement.classList.remove(oldTheme);
            document.documentElement.classList.add(currentTheme);
            updateButtonLabel(themeButton, currentTheme);
            console.log(`Theme changed to ${currentTheme}`);
        });
    }
});

function applyTheme(theme) {
    document.documentElement.classList.add(theme);
}

function updateButtonLabel(button, theme) {
    const span = button.querySelector("span");
    if (span) {
        span.textContent = theme === "dark" ? "Dark" : "Light";
    }
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    return localStorage.getItem("theme") || "light";
}
