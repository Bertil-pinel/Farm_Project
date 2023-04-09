<?php
namespace gui;

include_once "View.php";

class ViewProduct extends View
{
    public function __construct($layout, $name, $presenter)
    {
        parent::__construct($layout);



        $this->title= 'Products';

        $this->content = "<p> Hello $name </p>";

        $this->content .= $presenter->getPr();
    }
}