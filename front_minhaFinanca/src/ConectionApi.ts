const apiBase = "http://localhost:8080/api";

async function getSumary(){
    try {
        const response = await fetch(`${apiBase}/sumary`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching sumário:", error);
        return null;
    }
}

async function getTransation(){
    try {
        const response = await fetch(`${apiBase}/transations`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching receitas:", error);
        return null;
    }
}

async function postTransation(
  descricao: string,
  data: Date,
  valor: number,
  type: string,
  category: string
) {
  try {
    const payload = {
      descricao,
      data: data.toISOString().slice(0, 10), // Formata a data como "YYYY-MM-DD"
      valor,
      type,
      category,
    };

    const response = await fetch(`${apiBase}/transations`, {
      method: "POST",
      body: JSON.stringify(payload),
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) {
      const text = await response.text().catch(() => "");
      throw new Error(`HTTP ${response.status} - ${text}`);
    }

    const result = await response.json();
    return result;
  } catch (error) {
    console.error("Error posting transação:", error);
    return null;
  }
}

async function putTransation(
  id: number,
  descricao: string,
  data: Date,
  valor: number,
  type: string,
  category: string
) {
  try {
    const payload = {
      descricao,
      data: data.toISOString().slice(0, 10), // Formata a data como "YYYY-MM-DD"
      valor,
      type,
      category,
    };

    const response = await fetch(`${apiBase}/transations/${id}`, {
      method: "PUT",
      body: JSON.stringify(payload),
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) {
      const text = await response.text().catch(() => "");
      throw new Error(`HTTP ${response.status} - ${text}`);
    }

    const result = await response.json();
    return result;
  } catch (error) {
    console.error("Error updating transação:", error);
    return null;
  }
}

async function deleteTransation(id: number) {
    try {
        const response = await fetch(`${apiBase}/transations/${id}`, {
            method: "DELETE",
        });
        if (!response.ok) {
            const text = await response.text().catch(() => "");
            throw new Error(`HTTP ${response.status} - ${text}`);
        }
        return true;
    } catch (error) {
        console.error("Error deleting transação:", error);
        return false;
    }
}


async function getCategory(){
    try {
        const response = await fetch(`${apiBase}/category`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching despesas:", error);
        return null;
    }
}

async function postCategory(name: string){
    try {
        const response = await fetch(`${apiBase}/category`, {
            method: "POST",
            body: JSON.stringify({ name }),
            headers: {
                "Content-Type": "application/json"
            }
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error posting category:", error);
        return null;
    }

}

const api = {
    getSumary,
    getTransation,
    postTransation,
    putTransation,
    deleteTransation,
    getCategory,
    postCategory
};

export default api;