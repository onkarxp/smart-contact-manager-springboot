/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [
    // Your path to your html files
    "./scm2.0/src/main/resources/templates/**/*.html",

    // --- ADD THIS LINE ---
    // It tells Tailwind to scan Flowbite's JS files for classes
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    // --- ADD THIS LINE ---
    // This activates the Flowbite plugin
    require('flowbite/plugin')
  ],
}