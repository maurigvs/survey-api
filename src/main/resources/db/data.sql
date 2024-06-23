INSERT INTO public.survey (id, title) VALUES (1, 'Sample Survey');

INSERT INTO public.question (id, title, survey_id) VALUES (1, 'Where does Santa Claus live?', 1);
INSERT INTO public.question (id, title, survey_id) VALUES (2, 'Where you satisfied with your Christmas presents?', 1);
INSERT INTO public.question (id, title, survey_id) VALUES (3, 'Which city is the capital of Parana', 1);

INSERT INTO public.choice (id, title, question_id) VALUES (1, 'Hawaii', 1);
INSERT INTO public.choice (id, title, question_id) VALUES (2, 'Finland', 1);
INSERT INTO public.choice (id, title, question_id) VALUES (3, 'Sweden', 1);
INSERT INTO public.choice (id, title, question_id) VALUES (4, 'Istambul', 1);
INSERT INTO public.choice (id, title, question_id) VALUES (5, 'Very satisfied', 2);
INSERT INTO public.choice (id, title, question_id) VALUES (6, 'Neither satisfied or dissatisfied', 2);
INSERT INTO public.choice (id, title, question_id) VALUES (7, 'Dissatisfied', 2);
INSERT INTO public.choice (id, title, question_id) VALUES (8, 'Very dissatisfied', 2);
INSERT INTO public.choice (id, title, question_id) VALUES (9, 'Londrina', 3);
INSERT INTO public.choice (id, title, question_id) VALUES (10, 'Maringa', 3);
INSERT INTO public.choice (id, title, question_id) VALUES (11, 'Curitiba', 3);
INSERT INTO public.choice (id, title, question_id) VALUES (12, 'Foz do Iguacu', 3);

INSERT INTO public.answer (id, survey_id) VALUES (1, 1);

INSERT INTO public.answer_item (id, answer_id, choice_id, question_id) VALUES (1, 1, 2, 1);
INSERT INTO public.answer_item (id, answer_id, choice_id, question_id) VALUES (2, 1, 6, 2);
INSERT INTO public.answer_item (id, answer_id, choice_id, question_id) VALUES (3, 1, 10, 3);
