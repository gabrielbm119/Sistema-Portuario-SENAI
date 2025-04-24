document.addEventListener("DOMContentLoaded", () => {
    const btnAdmin = document.getElementById("btnAdmin");
    const btnCliente = document.getElementById("btnCliente");
  
    btnAdmin.addEventListener("click", () => {
      window.location.href = "pages/auth/login-admin.html";
    });
  
    btnCliente.addEventListener("click", () => {
      window.location.href = "pages/auth/login-cliente.html";
    });
  });