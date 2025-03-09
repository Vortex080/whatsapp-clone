/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('daisyui'),
  ],
  daisyui: {
    themes: [
      {
        mytheme: {

          "primary": "#25d366",

          "secondary": "#128c7e",

          "accent": "#111827",

          "neutral": "#dcf8c6",

          "base-100": "#ece5dd",

          "base-200": "#25d366",

          "base-300": "#25d366",

          "info": "#6b7280",

          "success": "#25d366",

          "warning": "#facc15",

          "error": "#dc2626",

          "whit": "#ffffff",
        },
      },
    ],
  },
}

