import { setError, setSuccess } from "./validate.js";

const main = document.querySelector(".main");
const logoutBtn = document.querySelector(".btn-logout");

const loginForm = document.querySelector(".login-form");
const login = document.getElementById("login");
const password = document.getElementById("password");

const currentUser = sessionStorage.getItem("currentUser");

const handleLogout = () => {
  sessionStorage.removeItem("currentUser");
  location.reload();
};

if (currentUser) {
  logoutBtn.classList.add("visible");
  logoutBtn.addEventListener("click", handleLogout);
  const html = `
    <div class="successful">
      <span>Добро пожаловать на LearnHub, ${JSON.parse(currentUser)}</span>
    </div>`;
  main.innerHTML = html;
}

const validateLoginData = (loginData) => {
  let isCorrectData = true;

  const users = JSON.parse(sessionStorage.getItem("users")) || [];
  console.log(loginData);
  console.log(users);
  const userData = users.find((user) => user.login === loginData.login);

  console.log(userData);

  if (!userData) {
    setError(login, "Пользователя с таким именем нет");
    isCorrectData = false;
  } else {
    setSuccess(login);
  }

  if (loginData.password === "") {
    setError(password, "Введите пароль");
    isCorrectData = false;
  } else if (userData.password !== loginData.password) {
    setError(password, "Неверный пароль");
    isCorrectData = false;
  } else {
    setSuccess(password);
  }

  return isCorrectData;
};

loginForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const signUpData = Object.fromEntries(new FormData(e.target));
  if (!validateLoginData(signUpData)) {
    return;
  } else {
    sessionStorage.setItem("currentUser", JSON.stringify(signUpData.login));
    main.textContent = `Добро пожаловать, ${signUpData.login}!`;
    location.reload();
  }
});
