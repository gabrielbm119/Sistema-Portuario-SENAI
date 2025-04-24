document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formLoginAdmin");
    const erroLogin = document.getElementById("erroLogin");
  
    form.addEventListener("submit", async (event) => {
      event.preventDefault();
  
      const email = document.getElementById("email").value;
      const senha = document.getElementById("senha").value;
  
      try {
        const response = await fetch("http://localhost:8083/admin");
        const admins = await response.json();
  
        const adminEncontrado = admins.find(
          (admin) => admin.emAdmin === email && admin.snAdmin === senha
        );
  
        if (adminEncontrado) {
          localStorage.setItem("adminId", adminEncontrado.idCliente);
          window.location.href = "../admin/home-admin.html";
        } else {
          erroLogin.textContent = "Credenciais inválidas.";
        }
      } catch (error) {
        erroLogin.textContent = "Erro ao conectar com o servidor.";
        console.error(error);
      }
    });
  });
  