-- =========================
-- DATOS INICIALES: CENTROS
-- =========================
INSERT INTO centros (id, nombre) VALUES
(1, 'Centro Ingreso'),
(2, 'Centro Norte'),
(3, 'Centro Sur');

-- =========================
-- DATOS INICIALES: PACIENTES
-- =========================
INSERT INTO pacientes (
    nuhsa,
    codigo_tao,
    sip2,
    nombre,
    primer_apellido,
    segundo_apellido,
    dni,
    modalidad_control,
    centro_id
) VALUES
(
    'NUHSA001',
    'TAO001',
    'SIP001',
    'Diego',
    'Pérez',
    'López',
    '12345678A',
    'Ambulatorio',
    1
),
(
    'NUHSA002',
    'TAO002',
    'SIP002',
    'Marcela',
    'García',
    'Ruiz',
    '23456789B',
    'Ingreso',
    2
),
(
    'NUHSA003',
    'TAO003',
    'SIP003',
    'Andres',
    'López',
    'Martín',
    '34567890C',
    'Seguimiento',
    3
);
