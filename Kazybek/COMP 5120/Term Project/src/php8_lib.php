<?php

function str_contains_($haystack, $needle) {
    if (strpos($haystack, $needle) !== false) {
        return true;
    }
    return false;
}

?>