const apiCliente = "http://localhost:8083/cliente";
const apiCarteira = "http://localhost:8083/carteira";

const clienteId = localStorage.getItem("clienteSelecionadoId");

document.addEventListener("DOMContentLoaded", () => {
  carregarCliente();
  carregarCarteira();
});

function carregarCliente() {
  fetch(`${apiCliente}/${clienteId}`)
    .then(res => res.json())
    .then(data => {
      document.getElementById("nomeCliente").textContent = data.nmCliente;
      document.getElementById("emailCliente").textContent = data.emCliente;
      document.getElementById("cnpjCliente").textContent = data.pjCliente;
      document.getElementById("telefoneCliente").textContent = data.tfCliente;

      document.getElementById("nmClienteEditar").value = data.nmCliente;
      document.getElementById("emClienteEditar").value = data.emCliente;
      document.getElementById("pjClienteEditar").value = data.pjCliente;
      document.getElementById("tfClienteEditar").value = data.tfCliente;
    })
    .catch(err => alert("Erro ao carregar cliente: " + err.message));
}

function carregarCarteira() {
  fetch(`${apiCarteira}`)
    .then(res => res.json())
    .then(carteiras => {
      const carteira = carteiras.find(c => c.clCarteira?.idCliente == clienteId);
      document.getElementById("saldoCarteira").textContent = carteira?.sdCarteira || "0.00";
    })
    .catch(err => alert("Erro ao carregar carteira: " + err.message));
}

function mostrarFormularioEdicao() {
  document.getElementById("formularioEdicao").style.display = "block";
}

function cancelarEdicao() {
  document.getElementById("formularioEdicao").style.display = "none";
}

function salvarEdicao() {
  const payload = {
    nmCliente: document.getElementById("nmClienteEditar").value,
    emCliente: document.getElementById("emClienteEditar").value,
    pjCliente: document.getElementById("pjClienteEditar").value,
    tfCliente: document.getElementById("tfClienteEditar").value,
    snCliente: "123456" // ou manter a anterior, se for necessário
  };

  fetch(`${apiCliente}/${clienteId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao atualizar cliente");
      return res.json();
    })
    .then(() => {
      alert("Cliente atualizado com sucesso!");
      cancelarEdicao();
      carregarCliente();
    })
    .catch(err => alert(err.message));
}

function deletarCliente() {
  if (confirm("Tem certeza que deseja deletar este cliente?")) {
    fetch(`${apiCliente}/${clienteId}`, { method: "DELETE" })
      .then(res => {
        if (!res.ok) throw new Error("Erro ao deletar cliente");
        alert("Cliente deletado com sucesso!");
        window.location.href = "clientes.html";
      })
      .catch(err => alert(err.message));
  }
}

function irParaFinanceiro() {
  window.location.href = "cliente-financeiro.html";
}

function irParaNavios() {
  window.location.href = "cliente-navios.html";
}
