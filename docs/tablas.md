# Las 52 tablas y su carpeta

Cada tabla del diagrama tiene su migración de Flyway y su carpeta (paquete) en los tres módulos. El nombre del paquete es el de la entidad en minúsculas, por ejemplo `country` para `countries`.

| # | Tabla | Entidad | Paquete | Endpoint | Migración |
|---|---|---|---|---|---|
| 1 | `countries` | `Country` | `country` | `/api/countries` | `V1__create_Country_table.sql` |
| 2 | `state_regions` | `StateRegion` | `stateregion` | `/api/state-regions` | `V2__create_StateRegion_table.sql` |
| 3 | `city_municipalities` | `CityMunicipality` | `citymunicipality` | `/api/city-municipalities` | `V3__create_CityMunicipality_table.sql` |
| 4 | `document_types` | `DocumentType` | `documenttype` | `/api/document-types` | `V4__create_DocumentType_table.sql` |
| 5 | `genders` | `Gender` | `gender` | `/api/genders` | `V5__create_Gender_table.sql` |
| 6 | `relationship_types` | `RelationshipType` | `relationshiptype` | `/api/relationship-types` | `V6__create_RelationshipType_table.sql` |
| 7 | `professional_types` | `ProfessionalType` | `professionaltype` | `/api/professional-types` | `V7__create_ProfessionalType_table.sql` |
| 8 | `studies` | `Study` | `study` | `/api/studies` | `V8__create_Study_table.sql` |
| 9 | `clinical_record_statuses` | `ClinicalRecordStatus` | `clinicalrecordstatus` | `/api/clinical-record-statuses` | `V9__create_ClinicalRecordStatus_table.sql` |
| 10 | `encounter_types` | `EncounterType` | `encountertype` | `/api/encounter-types` | `V10__create_EncounterType_table.sql` |
| 11 | `encounter_modalities` | `EncounterModality` | `encountermodality` | `/api/encounter-modalities` | `V11__create_EncounterModality_table.sql` |
| 12 | `encounter_statuses` | `EncounterStatus` | `encounterstatus` | `/api/encounter-statuses` | `V12__create_EncounterStatus_table.sql` |
| 13 | `risk_levels` | `RiskLevel` | `risklevel` | `/api/risk-levels` | `V13__create_RiskLevel_table.sql` |
| 14 | `treatment_statuses` | `TreatmentStatus` | `treatmentstatus` | `/api/treatment-statuses` | `V14__create_TreatmentStatus_table.sql` |
| 15 | `treatment_goal_statuses` | `TreatmentGoalStatus` | `treatmentgoalstatus` | `/api/treatment-goal-statuses` | `V15__create_TreatmentGoalStatus_table.sql` |
| 16 | `medication_routes` | `MedicationRoute` | `medicationroute` | `/api/medication-routes` | `V16__create_MedicationRoute_table.sql` |
| 17 | `assessment_types` | `AssessmentType` | `assessmenttype` | `/api/assessment-types` | `V17__create_AssessmentType_table.sql` |
| 18 | `consent_types` | `ConsentType` | `consenttype` | `/api/consent-types` | `V18__create_ConsentType_table.sql` |
| 19 | `diagnostic_systems` | `DiagnosticSystem` | `diagnosticsystem` | `/api/diagnostic-systems` | `V19__create_DiagnosticSystem_table.sql` |
| 20 | `professionals` | `Professional` | `professional` | `/api/professionals` | `V20__create_Professional_table.sql` |
| 21 | `professional_studies` | `ProfessionalStudy` | `professionalstudy` | `/api/professional-studies` | `V21__create_ProfessionalStudy_table.sql` |
| 22 | `contacts` | `Contact` | `contact` | `/api/contacts` | `V22__create_Contact_table.sql` |
| 23 | `phone_contacts` | `PhoneContact` | `phonecontact` | `/api/phone-contacts` | `V23__create_PhoneContact_table.sql` |
| 24 | `email_contacts` | `EmailContact` | `emailcontact` | `/api/email-contacts` | `V24__create_EmailContact_table.sql` |
| 25 | `patients` | `Patient` | `patient` | `/api/patients` | `V25__create_Patient_table.sql` |
| 26 | `patient_allergies` | `PatientAllergy` | `patientallergy` | `/api/patient-allergies` | `V26__create_PatientAllergy_table.sql` |
| 27 | `patient_contacts` | `PatientContact` | `patientcontact` | `/api/patient-contacts` | `V27__create_PatientContact_table.sql` |
| 28 | `clinical_records` | `ClinicalRecord` | `clinicalrecord` | `/api/clinical-records` | `V28__create_ClinicalRecord_table.sql` |
| 29 | `encounters` | `Encounter` | `encounter` | `/api/encounters` | `V29__create_Encounter_table.sql` |
| 30 | `clinical_notes` | `ClinicalNote` | `clinicalnote` | `/api/clinical-notes` | `V30__create_ClinicalNote_table.sql` |
| 31 | `mental_status_exams` | `MentalStatusExam` | `mentalstatusexam` | `/api/mental-status-exams` | `V31__create_MentalStatusExam_table.sql` |
| 32 | `risk_assessments` | `RiskAssessment` | `riskassessment` | `/api/risk-assessments` | `V32__create_RiskAssessment_table.sql` |
| 33 | `treatment_plans` | `TreatmentPlan` | `treatmentplan` | `/api/treatment-plans` | `V33__create_TreatmentPlan_table.sql` |
| 34 | `treatment_goals` | `TreatmentGoal` | `treatmentgoal` | `/api/treatment-goals` | `V34__create_TreatmentGoal_table.sql` |
| 35 | `priorities` | `Priority` | `priority` | `/api/priorities` | `V35__create_Priority_table.sql` |
| 36 | `conversations_statuses` | `ConversationStatus` | `conversationstatus` | `/api/conversations-statuses` | `V36__create_ConversationStatus_table.sql` |
| 37 | `sender_types` | `SenderType` | `sendertype` | `/api/sender-types` | `V37__create_SenderType_table.sql` |
| 38 | `message_types` | `MessageType` | `messagetype` | `/api/message-types` | `V38__create_MessageType_table.sql` |
| 39 | `ai_runs_statuses` | `AiRunStatus` | `airunstatus` | `/api/ai-runs-statuses` | `V39__create_AiRunStatus_table.sql` |
| 40 | `escalations_statuses` | `EscalationStatus` | `escalationstatus` | `/api/escalations-statuses` | `V40__create_EscalationStatus_table.sql` |
| 41 | `provider_models_ai` | `ProviderModelAi` | `providermodelai` | `/api/provider-models-ai` | `V41__create_ProviderModelAi_table.sql` |
| 42 | `ai_models` | `AiModel` | `aimodel` | `/api/ai-models` | `V42__create_AiModel_table.sql` |
| 43 | `chat_conversations` | `ChatConversation` | `chatconversation` | `/api/chat-conversations` | `V43__create_ChatConversation_table.sql` |
| 44 | `chat_participants` | `ChatParticipant` | `chatparticipant` | `/api/chat-participants` | `V44__create_ChatParticipant_table.sql` |
| 45 | `chat_messages` | `ChatMessage` | `chatmessage` | `/api/chat-messages` | `V45__create_ChatMessage_table.sql` |
| 46 | `chat_conversation_ai_settings` | `ChatConversationAiSetting` | `chatconversationaisetting` | `/api/chat-conversation-ai-settings` | `V46__create_ChatConversationAiSetting_table.sql` |
| 47 | `chat_ai_runs` | `ChatAiRun` | `chatairun` | `/api/chat-ai-runs` | `V47__create_ChatAiRun_table.sql` |
| 48 | `chat_ai_run_errors` | `ChatAiRunError` | `chatairunerror` | `/api/chat-ai-run-errors` | `V48__create_ChatAiRunError_table.sql` |
| 49 | `chat_ai_run_metrics` | `ChatAiRunMetric` | `chatairunmetric` | `/api/chat-ai-run-metrics` | `V49__create_ChatAiRunMetric_table.sql` |
| 50 | `chat_escalations` | `ChatEscalation` | `chatescalation` | `/api/chat-escalations` | `V50__create_ChatEscalation_table.sql` |
| 51 | `chat_escalation_assignments` | `ChatEscalationAssignment` | `chatescalationassignment` | `/api/chat-escalation-assignments` | `V51__create_ChatEscalationAssignment_table.sql` |
| 52 | `chat_escalation_status_history` | `ChatEscalationStatusHistory` | `chatescalationstatushistory` | `/api/chat-escalation-status-history` | `V52__create_ChatEscalationStatusHistory_table.sql` |

Cada paquete se repite en los tres módulos:

- `domain/.../<paquete>/model/aggregate`, `model/valueobject`, `event`, `exception` y `port/repository`
- `application/.../<paquete>/command`, `dto`, `exception` y `usecase`
- `infrastructure/.../<paquete>/adapters/in/rest`, `adapters/out/persistence` y `config`

Para comprobar que no falta ninguna, desde la raíz del proyecto:

```bash
ls infrastructure/src/main/resources/db/migration | wc -l        # 52
ls -d infrastructure/src/main/java/com/mindconnect/infrastructure/*/adapters | wc -l   # 52
ls -d domain/src/main/java/com/mindconnect/domain/*/model | wc -l                       # 52
```
