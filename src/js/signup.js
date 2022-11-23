import validateSignUp from "./validate.js";
import handlePhoneMask from "./phonemask.js";

const formContainer = document.querySelector(".form-container");
const signupForm = document.querySelector(".signup-form");
const login = document.getElementById("login");
const email = document.getElementById("email");
const password = document.getElementById("password");
const password2 = document.getElementById("password2");
const phone = document.getElementById("phone");
const signupBtn = document.querySelector(".btn-submit");

handlePhoneMask(phone);

signupForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const signUpData = Object.fromEntries(new FormData(e.target));
  const trimmedData = {};

  for (const key in signUpData) {
    trimmedData[key] = signUpData[key].trim();
  }

  const users = JSON.parse(sessionStorage.getItem("users")) || [];

  if (!validateSignUp()) {
    return;
  } else {
    users.push(trimmedData);
    sessionStorage.setItem("users", JSON.stringify(users));
    signupBtn.classList.add("active");
  }

  setTimeout(() => {
    const html = `
    <div class="successful">
      <span>Вы успешно зарегистрированы</span>
      <a href="./html/login.html">Вход</a>
    </div>`;
    formContainer.innerHTML = html;
  }, 1000);
});
