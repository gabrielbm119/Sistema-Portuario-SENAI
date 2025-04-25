const clienteId = localStorage.getItem("clienteLogadoId");
const apiUrl = "http://localhost:8083/agendamento";

document.addEventListener("DOMContentLoaded", () => {
  fetch(apiUrl)
    .then(res => res.json())
    .then(agendamentos => {
      const meusAgendamentos = agendamentos.filter(ag => ag.nvAgendamento?.clNavio?.idCliente == clienteId);
      renderizarAgendamentos(meusAgendamentos);
    })
    .catch(err => alert("Erro ao carregar agendamentos: " + err.message));
});

function renderizarAgendamentos(agendamentos) {
  const tabela = document.getElementById("tabelaAgendamentosCliente");
  tabela.innerHTML = "";

  agendamentos.forEach(ag => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${ag.idAgendamento}</td>
      <td>${ag.nvAgendamento?.nmNavio || "N/A"}</td>
      <td>${ag.dcAgendamento?.nmDoca || "N/A"}</td>
      <td>${ag.diAgendamento} ${ag.hiAgendamento}</td>
      <td>${ag.dfAgendamento} ${ag.hfAgendamento}</td>
      <td>${ag.stAgentamento}</td>
    `;
    tabela.appendChild(tr);
  });
}
