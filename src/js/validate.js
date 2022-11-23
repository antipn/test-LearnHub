export const setError = (element, message) => {
  const inputControl = element.parentElement;
  const errorBox = inputControl.querySelector(".error");

  errorBox.innerText = message;
  inputControl.classList.add("error");
  inputControl.classList.remove("success");
};

export const setSuccess = (element) => {
  const inputControl = element.parentElement;
  const errorBox = inputControl.querySelector(".error");
  errorBox.innerText = "";
  inputControl.classList.remove("error");
  inputControl.classList.add("success");
};

const validateSignUp = () => {
  const users = JSON.parse(sessionStorage.getItem("users")) || [];
  let isFormValid = true;

  const loginValue = login.value.trim();
  const emailValue = email.value.trim();
  const passwordValue = password.value.trim();
  const phoneValue = phone.value.trim();
  const password2Value = password2.value.trim();

  const isLoginBusy = users.some(({ login }) => login === loginValue);
  const isEmailBusy = users.some(({ email }) => email === emailValue);
  const isPhoneBusy = users.some(({ phone }) => phone === phoneValue);

  if (loginValue === "") {
    setError(login, "Требуется логин");
    isFormValid = false;
  } else if (isLoginBusy) {
    setError(login, "Данный логин уже занят");
    isFormValid = false;
  } else {
    setSuccess(login);
  }

  const isValidEmail = (email) => {
    const re =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  };

  if (emailValue === "") {
    setError(email, "Введите e-mail");
    isFormValid = false;
  } else if (!isValidEmail(emailValue)) {
    setError(email, "Некорректный e-mail");
    isFormValid = false;
  } else if (isEmailBusy) {
    setError(email, "Данный e-mail уже занят");
    isFormValid = false;
  } else {
    setSuccess(email);
  }

  if (phoneValue === "") {
    setError(phone, "Введите номер телефона");
    isFormValid = false;
  } else if (phoneValue.length < 18) {
    setError(phone, "Неверный формат телефона");
    isFormValid = false;
  } else if (isPhoneBusy) {
    setError(phone, "Данный телефон уже занят");
    isFormValid = false;
  } else {
    setSuccess(phone);
  }

  if (passwordValue === "") {
    setError(password, "Введите пароль");
    isFormValid = false;
  } else if (passwordValue.length < 8) {
    setError(password, "Пароль должен быть длиннее 8 символов");
    isFormValid = false;
  } else {
    setSuccess(password);
  }

  if (password2Value !== passwordValue) {
    setError(password2, "Пароли не совпадают");
    isFormValid = false;
  } else {
    setSuccess(password2);
  }

  return isFormValid;
};

export default validateSignUp;
