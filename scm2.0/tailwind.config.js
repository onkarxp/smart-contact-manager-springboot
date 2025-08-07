/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    // This path is now perfectly accurate for your templates folder:
    "scm2.0/src/main/resources/templates/**/*.html",
    
    // You can keep this if you ever put HTML files directly at the project root,
    // but based on your structure, it might not be strictly necessary for now.
    // "./*.html", 
  ],
  theme: {
    extend: {},
  },
  plugins: [],
  darkMode: 'class', // Enable dark mode support
}
