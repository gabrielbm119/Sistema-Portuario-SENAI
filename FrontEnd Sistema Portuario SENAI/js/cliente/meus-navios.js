const clienteId = localStorage.getItem("clienteLogadoId");
const apiUrl = "http://localhost:8083/navio";

document.addEventListener("DOMContentLoaded", () => {
  fetch(apiUrl)
    .then(res => res.json())
    .then(data => {
      const naviosCliente = data.filter(n => n.clNavio?.idCliente == clienteId);
      renderizarNavios(naviosCliente);
    })
    .catch(err => alert("Erro ao carregar navios: " + err.message));
});

function renderizarNavios(navios) {
  const tabela = document.getElementById("tabelaNaviosCliente");
  tabela.innerHTML = "";

  navios.forEach(n => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${n.idNavio}</td>
      <td>${n.nmNavio}</td>
      <td>${n.tpNavio}</td>
      <td>${n.cmNavio} m</td>
    `;
    tabela.appendChild(tr);
  });
}
