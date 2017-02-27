/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

 var whoIamBtn,
    projectBtn,
    reseauxBtn,
    whoIam,
    project,
    reseaux,
    github_repos,
    github_activeRepos,
    github_followers;

// Fonction permettant d'afficher ou de cacher les différentes pages, ainsi que de conserver un bouton en noir
function show(pin, unPin, toShow, toHide) {
    pin.classList.add("black");
    for (var i = unPin.length - 1; i >= 0; i--) {
        if (unPin[i] != null) {
            unPin[i].classList.remove("black");
        }
    }
    toShow.classList.remove("hide");
    for (var i = toHide.length - 1; i >= 0; i--) {
        if (toHide[i]) {
            toHide[i].classList.add("hide");
        }
    }
}

var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
        console.log('onDeviceReady');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        // Initialisation des variables globales et des évenements
        whoIamBtn = document.getElementById('whoIamBtn');
        projectBtn = document.getElementById('projetsBtn');
        reseauxBtn = document.getElementById('reseauxBtn');
        whoIam = document.getElementById('whoIam');
        project = document.getElementById('projets');
        reseaux = document.getElementById('reseaux');
        github_repos = document.getElementById('github-repos');
        github_activeRepos = document.getElementById('github-activeRepos');
        github_followers = document.getElementById('github-followers');

        whoIamBtn.addEventListener("click", function() {
            show(this, [projectBtn, reseauxBtn], whoIam, [project, reseaux]);
        });
        projectBtn.addEventListener("click", function() {
            show(this, [whoIamBtn, reseauxBtn], project, [whoIam, reseaux]);
        });
        reseauxBtn.addEventListener("click", function() {
            show(this, [projectBtn, whoIamBtn], reseaux, [project, whoIam]);
        });

        projectBtn.addEventListener("click", function() {
            // Récupération des données de session
            var datas = sessionStorage.getItem("dataStored");
            if (datas != null) {
                ajax.dataStored = JSON.parse(datas);
                var keys = Object.keys(ajax.dataStored);
                for (var key of keys) {
                    document.getElementById(key).innerHTML = ajax.dataStored[key];
                }
                return;
            }
            // Lancement des divers connexion et mise à jour du dom
            ajax.Update();
        });
        console.log('Received Event: ' + id);
    }
};
app.initialize();