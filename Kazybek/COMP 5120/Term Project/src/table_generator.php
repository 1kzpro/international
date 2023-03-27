<?php

function renderTable($result) {
    $sql_table_html = '';

    $fieldinfo = $result -> fetch_fields();

    $sql_table_html .= '<table class="table">';
    $sql_table_html .= '<thead><tr>';
    foreach($fieldinfo as $field) {
        $sql_table_html .= '<th scope="col">' . $field->name . '</th>';
    }
    $sql_table_html .= '<tr></thead>';

    $sql_table_html .= '<tbody>';
    $rows = $result->fetch_all(MYSQLI_ASSOC);
    foreach ($rows as $row) {
        $sql_table_html .= '<tr>';
        for ($i = 0; $i < count($fieldinfo); $i++) {
            $field = $fieldinfo[$i]->name;

            // console_log($field);
            // console_log("Row:" . $row);

            if ($i == 0) {
                $sql_table_html .= '<th scope="row">' . $row[$field] . '</th>';
                continue;
            }
            $sql_table_html .= '<td>' . $row[$field] . '</td>';
        }
        $sql_table_html .= '</tr>';
    }

    $sql_table_html .= '</tbody>';
    $sql_table_html .= '</table>';

    return $sql_table_html;
}

?>