// Variables globales
var modal = document.getElementById('monModal');
var element;
// Tableau des tâches
var taches = [];

// Fonction permettant d'ajotuer un élement en fonction d'un évènement
// ou d'une initialisation
function ajouterUnElement(evt, Id = "", nomDeLaTache = "", status = true, priority = 1) {
    var tache = document.getElementById('tache');
    if (nomDeLaTache == "" && tache.value == "") {
        return;
    }

    if (nomDeLaTache == "") {
        // En cas d'un évènement
        nomDeLaTache = tache.value;
    }
    tache.value = "";
    // Création des élements
    var parent = document.getElementById('todoList');
    var enfant = document.createElement('li');
    var barrer = document.createElement('span');
    var texte = document.createElement('span');
    var supprimer = document.createElement('span');
    // Ajout des élements à la page
    enfant.appendChild(barrer);
    enfant.appendChild(texte);
    enfant.appendChild(supprimer);
    parent.appendChild(enfant);
    // Ajout de textes et de paramètres aux éléments
    supprimer.innerHTML = "<i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i>";
    texte.innerHTML = nomDeLaTache;
    barrer.innerHTML = "<i class=\"fa fa-square-o\" aria-hidden=\"true\"></i>&nbsp;";
    enfant.id = Id;
    // Ajout d'événements
    barrer.addEventListener("click", barrerTexte);
    supprimer.addEventListener("click", supprimerTexte);
    texte.addEventListener("click", afficherModal);
    // initialisation des paramètres
    if (!status) {
        enfant.classList.add("fait");
        barrer.firstChild.classList.toggle("fa-check-square-o");
        barrer.firstChild.classList.toggle("fa-square-o");
    }
    if (priority == 1) {
        enfant.classList.add("bleu");
    }
    if (priority == 3) {
        enfant.classList.add("violet");
    }
    // Ajout des tâches au tableau
    if (evt) {
        taches.push(
            {
                "id": Id,
                "task": nomDeLaTache,
                "status": status,
                "priority": priority
            }
        );
        server.add({
                        "task": nomDeLaTache,
                        "status": status,
                        "priority": priority
                    });
    }
    console.log("ajouterUnElement");
}

// Récupérer l'index d'une tâche en fonction de son Id
function retounerIndex(id) {
    for(var i=0;i<taches.length;i++) {
        var tache = taches[i];
        if (tache['id'] == id) {
            return i;
        }
    }
    return -1;
}

// Pour barrer et débarrer le parent
function barrerTexte() {
    var i = retounerIndex(this.parentNode.id);
    var tache = taches[i];
    //tache['status']=!tache['status'];
    this.firstChild.classList.toggle("fa-check-square-o");
    this.firstChild.classList.toggle("fa-square-o");
    this.parentNode.classList.toggle("fait");
    server.edit(this.parentNode.id, {
                "task": tache["task"],
                "priority": tache["priority"],
                "status": (tache["status"] == "1" ? "0" : "1")
    });
    console.log("barrerTexte");
}
// Pour supprimer le parent
function supprimerTexte() {
    var i = retounerIndex(this.parentNode.id);
    taches.splice(i,1);
    server.del(this.parentNode.id);
    this.parentNode.remove();
    console.log("supprimerTexte");
}
// Pour afficher un modal
function afficherModal() {
    modal.style.display = "block";
    element = this;
    document.getElementById('champTxt').value = this.innerHTML;
    var i = retounerIndex(this.parentNode.id);
    var tache = taches[i];
    //document.getElementById('priorite').options[tache["priority"]].selected = true;
    document.getElementById('priorite').selectedIndex = tache["priority"] -1;
    console.log("afficherModal");
}
function Key(evt) {
    var keyName = evt.keyCode;
    //console.log(keyName)
    if (keyName === 13) {
        ajouterUnElement(evt);
        console.log("Key");
    }
}
var server = {
    import: function () {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "http://10.105.49.50:8090/api/v1/todos", true);
        xhr.onload = function (e) {
          if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                taches = JSON.parse(xhr.responseText);
                Init();
            } else {
              console.error(xhr.statusText);
            }
          }
        };
        xhr.onerror = function (e) {
          console.error(xhr.statusText);
        };
        xhr.send(null);
    },
    add: function(json) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://10.105.49.50:8090/api/v1/todo", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function (e) {
          if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                console.log(xhr.responseText);
            } else {
              console.error(xhr.statusText);
            }
          }
        };
        xhr.onerror = function (e) {
          console.error(xhr.statusText);
        };
        xhr.send(JSON.stringify(json));
    },
    del: function(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", "http://10.105.49.50:8090/api/v1/todo/" + id, true);
        xhr.onload = function (e) {
          if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
            } else {
              console.error(xhr.statusText);
            }
          }
        };
        xhr.onerror = function (e) {
          console.error(xhr.statusText);
        };
        xhr.send(null);
    },
    edit: function(id, json) {
        var xhr = new XMLHttpRequest();
        xhr.open("PUT", "http://10.105.49.50:8090/api/v1/todo/" + id, true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function (e) {
          if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                console.log(xhr.responseText);
            } else {
              console.error(xhr.statusText);
            }
          }
        };
        xhr.onerror = function (e) {
          console.error(xhr.statusText);
        };
        xhr.send(JSON.stringify(json));
    }
}
// Pour initialiser le ul à partir du tableau
function Init() {
    for(var tache of taches) {
        ajouterUnElement(null, tache["id"], tache["task"], tache["status"], tache["priority"]);
    }
    console.log("init");
}
function annuler() {
     modal.style.display = "none";
     console.log("annulerModal");
}
function valider() {
    var nouveauNom = document.getElementById('champTxt').value;
    var i = retounerIndex(element.parentNode.id);
    var tache = taches[i];
    tache['nom']=nouveauNom;
    element.innerHTML = nouveauNom;
    tache["priority"] = document.getElementById('priorite').selectedIndex +1;
    switch (tache["priority"]) {
        case 1:
            element.parentNode.classList.add("bleu");
            element.parentNode.classList.remove("violet");
        break;
        case 2:
            element.parentNode.classList.remove("bleu");
            element.parentNode.classList.remove("violet");
        break;
        case 3:
            element.parentNode.classList.remove("bleu");
            element.parentNode.classList.add("violet");
        break;
    }
    modal.style.display = "none";
    server.edit(element.parentNode.id, {
                "task": tache['nom'],
                "priority": tache["priority"]
    });
    console.log("validerModal");
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        console.log("onclickModal");
    }
}
//Init();
server.import();
document.getElementById('btnAnnuler').addEventListener("click", annuler);
document.getElementById('btnFermer').addEventListener("click", annuler);
document.getElementById('btnValider').addEventListener("click", valider);
document.getElementById('boutonAjout').addEventListener("click", ajouterUnElement);
document.getElementById('tache').addEventListener("keydown", Key);
