const apiUrl = "http://localhost:8083/cliente";

function loginCliente() {
  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;

  fetch(apiUrl)
    .then(res => res.json())
    .then(clientes => {
      const clienteEncontrado = clientes.find(
        (cliente) => cliente.emCliente === email && cliente.snCliente === senha
      );

      if (clienteEncontrado) {
        localStorage.setItem("clienteLogadoId", clienteEncontrado.idCliente);
        window.location.href = "../cliente/home-cliente.html";
      } else {
        alert("Credenciais inválidas");
      }
    })
    .catch(err => alert("Erro ao buscar clientes: " + err.message));
}
