http PUT http://localhost:8080/systems iua=CP9 description=Ref_tiers zone=REF
http PUT http://localhost:8080/systems iua=BZM description=Ref_lien_contrat zone=REF
http PUT http://localhost:8080/systems iua=FTO description=Ref_transverse_CCO zone=REF
http PUT http://localhost:8080/systems iua=EX7 description=Restructuration_Avant_Vente zone=VENTE
http PUT http://localhost:8080/systems iua=EXA description=Ortance zone=VENTE
http PUT http://localhost:8080/systems iua=FAF description=API_Restructuration zone=VENTE
http PUT http://localhost:8080/systems iua=FAP description=API_GED zone=COMM
http PUT http://localhost:8080/systems iua=DPZ description=API_Comm zone=COMM
http PUT http://localhost:8080/systems iua=FQ8 description=Espace_Regenair zone=VENTE
http PUT http://localhost:8080/systems iua=EWD description=API_Offre zone=VENTE
http PUT http://localhost:8080/systems iua=EWE description=API_Assurance zone=VENTE
http PUT http://localhost:8080/systems iua=EWF description=API_Simulation zone=VENTE
http PUT http://localhost:8080/systems iua=EWG description=API_Octroi zone=VENTE

http PUT http://localhost:8080/containers name=CP9-referentiel description=Services_recherche type=API
http PUT http://localhost:8080/containers name=BZM-referentiel description=Services_liens_contrat type=API
http PUT http://localhost:8080/containers name=FTO-signaletique description=Services_transverses_CCO type=API
http PUT http://localhost:8080/containers name=EX7-front description=IHM_Restruc type=IHM
http PUT http://localhost:8080/containers name=EX7-back description=BFF_Restruc type=IHM
http PUT http://localhost:8080/containers name=EXA-front description=IHM_Ortance type=IHM
http PUT http://localhost:8080/containers name=EXA-back description=BFF_Ortance type=IHM
http PUT http://localhost:8080/containers name=FAF-back description=API_Restruc type=API
http PUT http://localhost:8080/containers name=FAP-back description=API_GED type=API
http PUT http://localhost:8080/containers name=FQ8-front description=IHM_Espace_Regenair type=IHM
http PUT http://localhost:8080/containers name=FQ8-back description=BFF_Espace_Regenair type=IHM
http PUT http://localhost:8080/containers name=EWD-back description=API_Offre type=API
http PUT http://localhost:8080/containers name=EWE-back description=API_Assurance type=API
http PUT http://localhost:8080/containers name=EWF-back description=API_Simulation type=API
http PUT http://localhost:8080/containers name=EWG-back description=API_Octroi type=API

http PUT http://localhost:8080/systems/CP9/contains/CP9-referentiel
http PUT http://localhost:8080/systems/FTO/contains/FTO-signaletique
http PUT http://localhost:8080/systems/EX7/contains/EX7-front
http PUT http://localhost:8080/systems/EX7/contains/EX7-back
http PUT http://localhost:8080/systems/EXA/contains/EXA-front
http PUT http://localhost:8080/systems/EXA/contains/EXA-back
http PUT http://localhost:8080/systems/FQ8/contains/FQ8-front
http PUT http://localhost:8080/systems/FQ8/contains/FQ8-back
http PUT http://localhost:8080/systems/FAF/contains/FAF-back
http PUT http://localhost:8080/systems/FAP/contains/FAP-back
http PUT http://localhost:8080/systems/EWD/contains/EWD-back
http PUT http://localhost:8080/systems/EWE/contains/EWE-back
http PUT http://localhost:8080/systems/EWF/contains/EWF-back
http PUT http://localhost:8080/systems/EWG/contains/EWG-back

http PUT http://localhost:8080/systems/EX7/calls/FAF?type=ws
http PUT http://localhost:8080/systems/EX7/calls/FTO?type=ws
http PUT http://localhost:8080/systems/FQ8/calls/FAF?type=ws
http PUT http://localhost:8080/systems/FQ8/calls/FAP?type=ws
http PUT http://localhost:8080/systems/FQ8/calls/CP9?type=ws
http PUT http://localhost:8080/systems/FQ8/calls/DPZ?type=ws
http PUT http://localhost:8080/systems/FQ8/calls/FQ8?type=ws
http PUT http://localhost:8080/systems/EXA/calls/FAP?type=ws
http PUT http://localhost:8080/systems/EXA/calls/EWD?type=ws
http PUT http://localhost:8080/systems/EXA/calls/EWE?type=ws
http PUT http://localhost:8080/systems/EXA/calls/EWF?type=ws
http PUT http://localhost:8080/systems/EXA/calls/EWG?type=ws
http PUT http://localhost:8080/systems/EXA/calls/FTO?type=ws
http PUT http://localhost:8080/systems/EXA/calls/CP9?type=ws
http PUT http://localhost:8080/systems/EXA/calls/BZM?type=ws
http PUT http://localhost:8080/systems/CP9/calls/FTO?type=ws

